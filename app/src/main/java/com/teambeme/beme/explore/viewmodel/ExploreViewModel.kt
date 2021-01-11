package com.teambeme.beme.explore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teambeme.beme.explore.model.OtherQuestionsData
import com.teambeme.beme.explore.model.ResponseExplorationAnswers
import com.teambeme.beme.explore.model.ResponseExplorationQuestions
import com.teambeme.beme.explore.repository.ExploreRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreViewModel(private val exploreRepository: ExploreRepository) : ViewModel() {
    private val _otherMindsList = MutableLiveData<List<ResponseExplorationAnswers.Data>>()
    val otherMindsList: LiveData<List<ResponseExplorationAnswers.Data>>
        get() = _otherMindsList

    private val _otherQuestionsList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>()
    val otherQuestionsList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>
        get() = _otherQuestionsList

    private var _chipChecked = mutableListOf(false, false, false, false, false, false)
    val chipChecked: MutableList<Boolean>
        get() = _chipChecked

    private var categoryNum: Int? = null

    private var sortingText: String = "최신"

    fun setCategoryNum(category: Int) {
        chipChecked[category - 1] = !chipChecked[category - 1]
        if (!chipChecked[0] && !chipChecked[1] && !chipChecked[2] && !chipChecked[3] && !chipChecked[4] && !chipChecked[5]) {
            categoryNum = null
        } else {
            categoryNum = category
        }
        Log.d("category_func_1", chipChecked.toString())
        Log.d("category_func_2", categoryNum.toString())
        requestOtherQuestionsWithCategorySorting(categoryNum, sortingText)
    }

    fun setSortingText(sorting: String) {
        sortingText = sorting
        requestOtherQuestionsWithCategorySorting(categoryNum, sortingText)
    }

    private val _otherAnswersList =
        MutableLiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>()
    val otherAnswersList: LiveData<MutableList<ResponseExplorationQuestions.Data.Answer>>
        get() = _otherAnswersList

    fun requestOtherMinds() {
        exploreRepository.getExplorationAnother("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI")
            .enqueue(
                object : Callback<ResponseExplorationAnswers> {
                    override fun onResponse(
                        call: Call<ResponseExplorationAnswers>,
                        response: Response<ResponseExplorationAnswers>
                    ) {
                        if (response.isSuccessful)
                            _otherMindsList.value = response.body()!!.data?.toList()
                    }

                    override fun onFailure(call: Call<ResponseExplorationAnswers>, t: Throwable) {
                        Log.d("network_requestOtherMinds", "통신실패")
                    }
                }
            )
    }

    fun requestOtherQuestions() {
        exploreRepository.getExplorationOtherQuestions(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            1,
            null,
            "최신"
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        if (response.isSuccessful) {
                            _otherQuestionsList.value =
                                response.body()!!.data?.answers?.toMutableList()
                            Log.d(
                                "network_requestOtherQuestionsCategory",
                                _otherQuestionsList.value.toString()
                            )
                        }
                    }

                    override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                        Log.d("network_requestOtherQuestions", "통신실패")
                    }
                }
            )
    }

    fun requestOtherQuestionsWithCategorySorting(category: Int?, sorting: String) {
        exploreRepository.getExplorationOtherQuestions(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiaWF0IjoxNjEwMjk4ODkzLCJleHAiOjE2NDE4MzQ4OTMsImlzcyI6ImJlbWUifQ.hR-HzFpSO6N97Y-7c_l3cUkFvXdtVMuDmAOhTaRhAhI",
            1,
            category,
            sorting
        )
            .enqueue(
                object : Callback<ResponseExplorationQuestions> {
                    override fun onResponse(
                        call: Call<ResponseExplorationQuestions>,
                        response: Response<ResponseExplorationQuestions>
                    ) {
                        if (response.isSuccessful) {
                            _otherQuestionsList.value =
                                response.body()!!.data?.answers?.toMutableList()
                        }

                    }

                    override fun onFailure(call: Call<ResponseExplorationQuestions>, t: Throwable) {
                        Log.d("network_requestOtherQuestions", "통신실패")
                    }
                }
            )
    }

    private val dummyOtherQuestionsList = mutableListOf(
        OtherQuestionsData(
            userId = "1",
            category = "가치관",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "2",
            category = "사랑",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "26",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "3",
            category = "일상",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "15",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "4",
            category = "이야기",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "3",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "5",
            category = "미래",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "4",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "6",
            category = "의미",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "7",
            category = "일상",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "15",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "8",
            category = "이야기",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "3",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "9",
            category = "미래",
            title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
            content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
            time = "4",
            isBookmarked = false,
            isAnswered = true
        ),
        OtherQuestionsData(
            userId = "10",
            category = "의미",
            title = "올해 1월과 가장 달라진 점은 무엇인가요?",
            content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
            time = "5",
            isBookmarked = false,
            isAnswered = true
        )
    )

    fun plusDummyOtherQuestions() {
        val plusOtherQuestionsList = listOf(
            OtherQuestionsData(
                userId = "11",
                category = "가치관",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "12",
                category = "사랑",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "26",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "13",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "14",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "15",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "16",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "17",
                category = "일상",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "15",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "18",
                category = "이야기",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "3",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "19",
                category = "미래",
                title = "과거, 미래 둘 중 하나로 시간 여행을 할 수 있다면 무엇을 선택할 것인가요?",
                content = "과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재, 과거, 미래, 현재",
                time = "4",
                isBookmarked = false,
                isAnswered = true
            ),
            OtherQuestionsData(
                userId = "20",
                category = "의미",
                title = "올해 1월과 가장 달라진 점은 무엇인가요?",
                content = "나이, 나이, 나이, 나이, 나이, 나이, 나이",
                time = "5",
                isBookmarked = false,
                isAnswered = true
            )
        )
        dummyOtherQuestionsList.addAll(plusOtherQuestionsList.toMutableList())
    }
}