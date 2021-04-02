<h1 align="center"> BeMe </h1>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.4.32-yellowgreen?logo=kotlin"/>
  <img src="https://img.shields.io/badge/Android-4.1.3-blue?logo=Android+Studio"/>
  <img src="https://img.shields.io/badge/targetSdk-30-green?logo=Android"/>
  <img src="https://img.shields.io/badge/minSdk-26-green?logo=Android"/>
  <img src="https://github.com/TeamBeMe/BeMeAndroid/actions/workflows/build.yml/badge.svg?branch=develop"/>
</p>

<p align="center">
  <h2 align="center">나를 알아가는 질문, 나를 돌아보는 시간 BeMe</h2>
  <p align="center">
  나는 어떻게 살고 싶은 지, 무엇을 중요하게 생각하는 지 <br />
  어떤 사람과 함께하고 싶고, 삶을 어떻게 마무리하고 싶은 지 <br />
  "나"답게 살고 싶다는 생각을 하지만 정작 '나'를 알아간다는 건 쉽지 않습니다. <br />
  그러나, BeMe를 사용하는 여러분들은 매일 제공되는 질문들로 자신을 성찰하고 알아가게 되는 경험을 느낄 수 있을 것입니다. <br />
  </p>
</p>
<br/>

<h1 align="center"> BeMeRoid </h1>

<table align="center">
  <th align="center">이현우</th>
  <th align="center">손연주</th>
  <th align="center">김우빈</th>
  <th align="center">이세민</th>
  <tr>
    <td align="center">
      <img src= "https://user-images.githubusercontent.com/54518925/103661562-36608400-4fb2-11eb-9534-6633ae33dde9.jpeg"
           width = "700px" height="auto"/>
    </td>
    <td align="center">
      <img src= "https://user-images.githubusercontent.com/54518925/103661566-3791b100-4fb2-11eb-8d88-b16867340ad2.jpeg"
           width = "700px" height="auto"/>
    </td>
    <td align="center">
      <img src= "https://user-images.githubusercontent.com/54518925/103661568-382a4780-4fb2-11eb-9f17-88cfb216a537.jpg"
           width = "700px" height="auto"/>
    </td>
    <td align="center">
      <img src= "https://user-images.githubusercontent.com/54518925/103661573-395b7480-4fb2-11eb-82b1-090cd9389663.jpg"
           width = "700px" height="auto"/>
    </td>
  </tr>
  <tr>
  	<td>
  	  <p>
        BeMe 안드로이드 리드 개발자
      </p>
      <p>
        OUNCE 안드로이드 리드 개발자
      </p>
      <p>
        MARU 안드로이드 리드 개발자
      </p>
      <p>
        건국대학교 컴퓨터공학과 2학년
      </p>
    </td>
    <td>
      <p>
        BeMe 안드로이드 개발자
      </p>
      <p>
        서울여자대학교 디지털미디어학과 소프트웨어융합학과 (복수전공) 3학년
      </p>
    </td>
    <td>
      <p>
        BeMe 안드로이드 개발자
      </p>
      <p>
        상명대학교 컴퓨터과학과
      </p>
    </td>
    <td>
      <p>
        BeMe 안드로이드 개발자
      </p>
      <p>
        한양대학교 산업공학과(창업융합전공 다중전공) 3학년
      </p>
    </td>
  </tr>
  <tr>
    <td>
      <ul>
        <li> 홈 </li>
        <li> 글쓰기 </li>
        <li> 로그인 & 회원가입 뷰 구현 </li>
        <li> 프로젝트 Setting </li>
        <li> Util 기능 </li>
      </ul>
    </td>
    <td>
      <ul>
        <li> 탐색탭 </li>
        <li> 팔로잉탭 </li>
        <li> 같은 질문에 대한 다른 답변들 </li>
        <li> 팔로잉탭 모두보기 </li>
      </ul>
    </td>
    <td>
      <ul>
        <li> 마이페이지 </li>
        <li> 상세페이지 </li>
        <li> 타인 프로필  </li>
        <li> 설정 창 </li>
      </ul>
    </td>
    <td>
      <ul>
        <li> 알림 뷰 </li>
        <li> 푸시 알림 </li>
        <li> 아이디 검색 후 팔로잉 </li>
      </ul>
    </td>
  </tr>
</table>

## BeMe's Coding Convention
### Kotlin Style Guide
BeMe는 [Google의 Kotlin Coding Style Guide](https://developer.android.com/kotlin/style-guide)를 따릅니다

### Class Layout
다음과 같은 순서 클래스를 구성합니다
- Property 선언과 초기화 블럭(intializer blocks)
- 추가적인 생성자
- 메소드 정의
- 컴패니언 오브젝트(Companion object)

### Naming Rule
#### Package
- 패키지의 이름은 항상 소문자로 하고, 밑줄을 사용하지 않습니다
- 두 개 이상의 단어를 한 번에 사용하는 것을 지양합니다

#### Class/Object
- Pascal Case
```
class BeMe
open class BeMeParent { /* ... */ }
object MoreSampleName : SampleName() { /* ... */ }
```

#### Function/Parameter/Variable
- Camel Case
```
val initList = mutableList<BeMeData>()
fun getList: List<BeMeData>() { /* ... */ }
```
#### Constant
- Upper Snake Case
- 상수는 companion objet에 넣어 보관합니다
```
companion object {
    const val MAX_COUNT = 8
}
```

### Formatting
#### Indenting
Tab 키를 써서 Indenting 합니다

#### 중괄호
괄호 뒤에 한 칸을 띄우고 사용합니다
```
if (elements != null) {
    for (element in elements) {
        // ...
    }
}
```

#### 괄호
- Control문(if/while/for)
    - 한 칸 띄어씁니다
- 생성자/Method
    - 붙여씁니다
```
if (isSpacing == true) { /*...*/ }
fun isSpacing() { /*...*/ }
```

#### Colon(:)
- 변수의 타입/함수 리턴 타입 결정
    - 콜론을 앞에 붙인다
- 상속받은 클래스/인터페이스 구분
    - 한 칸 띄어쓴다
```
fun isSpacing(): Boolean { /*...*/ }
class MainActivity : AppCompatActivity()
```


## BeMe's Git Commit Message Convention

### Base Structure

```
#{issue_number} [TYPE] : subject

body(optional)

footer(optional)
```

### Issue Number
- BeMe는 이슈 단위 커밋으로 기능 개발을 관리합니다
- GitHub에서 이슈를 트래킹을 할 수 있게 Issue Number를 커밋 메시지에 넣어줍니다

### TYPE
- FEAT: 새로운 기능 개발
- FIX: 버그 수정
- DOCS: 문서 수정/추가
- STYLE: 스타일(xml file) 코드 변경
- REFACTOR: 리팩토링
- TEST: 테스트 코드 추가/테스트 리팩토링(Room, Retrofit 동작 확인을 위한)
- CHORE: Gradle이나 설정 세팅할 때

### Subject
- Subject는 50글자를 넘어가면 안된다
- 첫 시작은 대문자로 해야한다
- 마지막에 마침표(.)를 찍으면 안된다
- 어떤 변경점이 있는지 설명한다
- 명령조를 사용한다

### Body
- 부연 설명이나 커밋의 이유를 설명할 때만 사용
- Not How, Explauin What and Why
- 윗 부분과 1줄의 공백 필요
- 각 라인은 72자 초과 불가

### Footer
- Issue Tracker IDs를 적을 때 사용

## BeMe's Git Branch Strategy: Git-Flow
<img src = "https://user-images.githubusercontent.com/54518925/103665498-d5877a80-4fb6-11eb-81ad-de0c1a577083.png" />
<img src = "https://user-images.githubusercontent.com/54518925/103665503-d6b8a780-4fb6-11eb-9786-9b97bc83ceda.png" />
<img src = "https://user-images.githubusercontent.com/54518925/103665534-dd471f00-4fb6-11eb-9246-7fe89cf2aff0.png" />

## Tech Spec

- App Architecture based on MVVM Architecture
<p align="center">
  <img src="https://user-images.githubusercontent.com/54518925/104303381-5db4d500-550d-11eb-86b7-b8bda29a1987.png" style="zoom:50%;" />
</p>


- Android Jetpack
  - LifeCycle(ViewModel, LiveData, LifeCycleObserver)
  - Room
  - DataBinding
  - Navigation Component
  - ViewPager2
  - Android KTX
  - Hilt

- ktlint

- Glide

- OkHttp/Retrofit2

- Kotlin Coroutine

- Gson

- CircleImageView

- ImageCrop

- Lottie

- TedPermission

- Firebase/Firebase Cloud Messaging

- StickyNestedScrollView

## 구현 기술
- [Room을 이용한 글 임시저장 구현](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/RoomDataConnection.md)
- [BaseViewContoller로 쉬운 DataBinding과 생명주기 관찰](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/BaseViewController.md)
- [NestedScrollableHost로 중첩 ViewPager2 이슈 해결](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/NestedScrollableHost.md)
- [Status Bar의 색상 바꾸기](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/StatusBarUtil.md)
- [BindingAdapter로 View의 속성 정의, 설정](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/BindingAdapter.md)
- [DiffUtil을 적용한 ListAdapter](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/ListAdapterWithDiffUtil.md)
- [검색 필터 바텀시트 구성하고 뷰모델을 통해 값을 받아와 봅시다](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/BottomSheetDialogFragment.md)
- [맨 밑까지 스크롤을 할 때 더보기 버튼 띄우기](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/ScrollAddMoreButton.md)
- [SnapHelper로 RecyclerView에 ViewPager 효과 주기](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/SnapHelperWithRecyclerView.md)
- [이미지를 원하는 비율로 잘라봅시다, Image Cropper를 이용해서](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/ImageCropper.md)
- [MultiPart 서버통신으로 Image 서버로 날려버리기](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/Multipart.md)
- [이메일 양식받아서 문의 이메일 보내기](https://github.com/TeamBeMe/BeMeAndroid/blob/develop/document/IntentEmail.md)

