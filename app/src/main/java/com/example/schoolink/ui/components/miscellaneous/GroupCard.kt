package com.example.schoolink.ui.components.miscellaneous

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.schoolink.R
import com.example.schoolink.domain.models.GroupModel
import com.example.schoolink.domain.models.GroupType
import com.example.schoolink.ui.theme.*

@Composable
fun GroupCard(
    group: GroupModel,
    showTopLine: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = White,
    ) {
        Column {
            if (showTopLine) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(0.5.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Gravel),
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            val groupImage = if (!group.groupPicturePath.isNullOrEmpty()) {
                rememberAsyncImagePainter(model = group.groupPicturePath)
            } else {
                painterResource(id = R.drawable.ic_group)
            }

            Image(
                painter = groupImage,
                contentDescription = "${group.groupName} group picture",
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
                    text = group.groupName,
                    color = Black,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = group.groupType!!.name,
                    color = Ash,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = "Trailing icon",
                tint = Black,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


@Preview
@Composable
private fun GroupCardPreview() {

//    val dummyStudent = StudentModel(
//        id = 0,
//        email = "asd",
//        firstName = "Uros",
//        lastName = "Lazarevic",
//        dateOfBirth = "14/12/1999",
//        description = "description",
//        gender = Gender.MALE,
//        profilePicturePath = null,
//        studentCode = "A2D53AC"
//    )
    val dummyGroup = GroupModel(
        groupId = 0,
        groupName = "Name of group",
        groupType = GroupType.ADVANCED,
    )
    GroupCard(
        dummyGroup,
        onClick = {}
    )
}