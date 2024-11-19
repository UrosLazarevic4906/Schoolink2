package com.example.schoolink.ui.components.miscellaneous

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.schoolink.domain.models.StudentModel
import com.example.schoolink.R
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.ui.theme.*
@Composable
fun StudentCard(
    student: StudentModel,
    trailingIcon: Painter? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.background),
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ){
            val profilePicture = if(student.profilePicturePath != null) {
                painterResource(id = R.drawable.ic_user) // TODO: promeniti u sliku usera
            } else {
                painterResource(id = R.drawable.ic_user)
            }

            Image(
                painter = profilePicture,
                contentDescription =  "${student.studentCode} profile picture",
                modifier = Modifier
                    .size(48.dp)
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
                    tint = Black
                )
            }
        }
    }
}

@Preview
@Composable
private fun StudentCardPreview() {

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

    StudentCard(
        student = dummyStudent,
        trailingIcon = painterResource(R.drawable.ic_pen)
    )
}