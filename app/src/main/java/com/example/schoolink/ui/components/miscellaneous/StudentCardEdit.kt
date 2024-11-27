package com.example.schoolink.ui.components.miscellaneous

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.schoolink.domain.models.StudentModel
import com.example.schoolink.R
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.ui.theme.*
@Composable
fun StudentCardEdit(
    student: StudentModel,
    trailingIcon: Painter? = null,
    showTopLine: Boolean = false
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth().clickable {  },
        color = White,
    ) {
        Column {
            if(showTopLine) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(0.5.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Gravel),
                )
            }
        }
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ){

            val profilePicture = if(!student.profilePicturePath.isNullOrEmpty()) {
                rememberAsyncImagePainter(model = student.profilePicturePath)
            } else {
                painterResource(id = R.drawable.ic_user)
            }

            Image(
                painter = profilePicture,
                contentDescription =  "${student.studentCode} profile picture",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${student.firstName} ${student.lastName}",
                    color = Black,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = student.studentCode,
                    color = Ash,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
            }

            trailingIcon?.let {
                Icon(
                    painter = it,
                    contentDescription = "Trailing icon",
                    tint = Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun StudentCardEditPreview() {

    val dummyStudent = StudentModel(
        id = 0,
        email = "asd",
        firstName = "Uros",
        lastName = "Lazarevic",
        dateOfBirth = "14/12/1999",
        description = "description",
        gender = Gender.MALE,
        profilePicturePath = null,
        studentCode = "A2D53AC"
    )

    StudentCardEdit(
        student = dummyStudent,
        trailingIcon = painterResource(R.drawable.ic_pen)
    )
}