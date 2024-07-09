import com.example.nonameapp.model.DataLogin

data class RegisterResponse(
    val message: String,
    val code: Int,
    val data: List<DataLogin>,
)
