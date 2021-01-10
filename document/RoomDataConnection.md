# Room을 이용한 글 임시저장 구현

## 문제점 정의

답변을 작성하다 홈 화면으로 나가거나 앱을 강제 종료해도 현재 작성하던 글은 임시저장이 되어야 한다.

우선 강제 종료를 해도 저장이 되어야 한다는 점에서 임시저장을 서버로 구현하기에는 무리가 있다는 생각이 들었다. 또한 모든 게시글이 동일한 기능을 사용해야한다는 점에서 SharedPreference, DataStore도 적용이 힘들다는 생각이 들었다. 그래서 임시저장을 앱 내 DB인 Room을 이용하여 구현하고자 한다.



## 해결 로직

제일 먼저 들었던 생각은 글자를 입력할 때마다 DB에 저장하는 것이었다. 이러면 확실히 모든 내용이 DB에 들어가기 때문이다. 그러나 DB 입출력에 시간이 오래걸린다는 한계로 인해 에러가 뜨고 다른 방법을 모색했다. 그리고 Activity가 종료될 때 onPause가 호출되고 그 곳에서 통신해서 저장할 수 있다는 것을 알게되어

> 작성 중인 글 내용 받아오기
>
> -> 텍스트를 답변 아이디와 함께 DB에 저장
>
> -> 다시 앱에 들어가서 해당 답변 창을 띄울 때 답변 id에 해당하는 텍스트를 DB에서 가져와 Intent로 넘겨
>
> -> 답변 Acitivty에 붙여주기

다음과 같은 로직으로 임시저장 기능을 구현했다.

## 어떻게 작성 중인 글 내용을 가져오지?

### InverseBindingAdapter 활용

````kotlin
@InverseBindingAdapter(attribute = "android:text", event = "textAttrChanged")
@JvmStatic
fun getEditTextString(view: EditText): String = view.text.toString()

// xml에서 사용
<EditText
            android:id="@+id/txt_answer_answer"
						...
            android:gravity="top"
            android:hint="답변을 써주세요"
            android:text="@={answerViewModel.answer}"
            android:textColor="@color/answer_text_black" />
````

다음과 같이 ```android:text="@={answerViewModel.answer}"```로 속성을 정의하여 EditText의 text를 ViewModel의 변수에 넘겨줄 수 있었다.



## Room 구현

Room은 DAO를 활용하여 App내 DB의 접근하는 방식으로 구현한다.

```kotlin
// Entity
@Entity(tableName = "answer_record_table")
data class AnswerData(
    @PrimaryKey
    var questionId: Long,
    @ColumnInfo(name = "answer")
    var answer: String
)

// DAO
@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(answerData: AnswerData)

    @Update
    fun update(answerData: AnswerData)

    @Query("SELECT * FROM answer_record_table WHERE questionId = :key")
    suspend fun get(key: Long): AnswerData?

    @Query("DELETE FROM answer_record_table WHERE questionID = :key")
    fun delete(key: Long)
}

// App Database
@Database(entities = [AnswerData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val answerDao: AnswerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "answer record database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

```



## ViewModel에서 DB 입출력

ViewModel에서 DB와 통신할 수 있는 DAO를 넣어주고 이를 활용하여 DB에서 데이터를 가져오고 저장한다. 이 때 DB에서 데이터를 메인 스레드에서 입출력을 할 때 스레드가 블럭되어 ANR이 발생될 수 있으므로 Coroutine을 활용하여 DB 통신을 한다.

```kotlin
class AnswerViewModel(
    private val dataBase: AnswerDao
) : ViewModel() {
    var answer: String = ""
    private var questionId = -1

    suspend fun initEditText(id: Int): String {
        return viewModelScope.async {
            answer = getStoredAnswer(id.toLong())?.answer ?: ""
            questionId = id
            answer
        }.await()
    }

    private suspend fun getStoredAnswer(id: Long): AnswerData? {
        return withContext(Dispatchers.IO) { dataBase.get(id) }
    }

    fun storeAnswer() {
        viewModelScope.launch {
            dataBase.insert(
                AnswerData(
                    questionId = questionId.toLong(),
                    answer = answer
                )
            )
        }
    }
}
```



## onPause에서 저장

```kotlin
override fun onPause() {
    super.onPause()
    answerViewModel.storeAnswer()
}
```



## 구현 화면

<img src = "https://user-images.githubusercontent.com/54518925/103985398-3340e000-51cc-11eb-9543-745632f439a8.gif" width = "40%"/>