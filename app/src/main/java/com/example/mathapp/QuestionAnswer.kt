import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionAnswer(
    val question: String,
    val userAnswer: Int,
    val correctAnswer: Int,
    val isCorrect: Boolean
) : Parcelable
