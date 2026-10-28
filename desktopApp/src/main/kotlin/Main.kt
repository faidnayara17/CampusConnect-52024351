import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kharisma.campusconnect.shared.CourseRepositoryImpl
import com.kharisma.campusconnect.shared.getPlatformName

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "CampusConnect Desktop") {
        val repo = CourseRepositoryImpl()
        Column(Modifier.padding(16.dp)) {
            Text("Berjalan di: ${getPlatformName()}")
            repo.getAllCourses().forEach { mk ->
                Text("${mk.nama} — ${mk.sks} SKS")
            }
        }
    }
}
