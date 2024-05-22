package com.jaguh.someapp

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jaguh.someapp.navigation.Home
import com.jaguh.someapp.navigation.Step3

@Preview(showSystemUi = true)
@Composable
fun Step2Screen(navController: NavController) {
    val context = LocalContext.current
    var mSelectedName by remember { mutableStateOf("") }
    var mSelectedDomain by remember { mutableStateOf("") }
    var mSelectedNo by remember { mutableStateOf(false) }
    var mSelectedAbout by remember { mutableStateOf("") }


    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column (
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ){

            Spacer(modifier = Modifier.height(20.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Step 2/4",
                    color = Color.Gray
                )
                Row {
                    Text(
                        text = "Language for website generation: ",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "English",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {

                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = mSelectedName,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { mSelectedName = it},
                label = { Text(text = "Enter your name", color = Color.Gray) },
                )

            Spacer(modifier = Modifier.height(32.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Tell us about yourself",
                    color = Color.Black,
                    fontSize = 14.sp
                )

                Row(
                    modifier = Modifier.clickable {
                        if (mSelectedAbout.length > 20){
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Min 20 characters", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.enhancewithai),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Enhance with AI",
                        textDecoration = TextDecoration.Underline
                    )
                }
            }

            Spacer(modifier = Modifier.height(1.dp))

            OutlinedTextField(
                value = mSelectedAbout,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { mSelectedAbout = it},
                label = { Text(
                    text = "Tell us about yourself",
                    color = Color.Gray
                ) },
            )


            Spacer(modifier = Modifier.height(32.dp))

            Row {

                OutlinedTextField(
                    value = mSelectedDomain,
                    modifier = Modifier
                        .fillMaxWidth(.55f)
                        .height(50.dp),
                    onValueChange = {},
                    label = {
                        Text(
                            text = "Do you own a domain?",
                            color = Color.Black,
                            fontSize = 12.sp
                        )
                    },
                )

                Spacer(modifier = Modifier.width(6.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(text = "Yes")
                }
                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { mSelectedNo = !mSelectedNo },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(text = "No")
                }

            }

            Spacer(modifier = Modifier.height(32.dp))

            AnimatedVisibility(visible = mSelectedNo) {
                OutlinedTextField(
                    value = mSelectedDomain,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {},
                    label = {
                        Text(
                            text = "Enter the desired Domain",
                            color = Color.Black,
                            fontSize = 12.sp
                        )
                    },
                )
            }

            Spacer(modifier = Modifier.height(100.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Button(
                    onClick = {
                              navController.navigate(Home.route)
                    },
                    modifier = Modifier,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.Gray
                    )
                ) {
                    Text(text = "Back")

                }

                Button(
                    onClick = {
                        if (mSelectedName.isNotEmpty() && mSelectedAbout.isNotEmpty()){
                            navController.navigate(Step3.route)
                        } else {
                            Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
                        }

                    },
                    modifier = Modifier,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (mSelectedName.isEmpty() && mSelectedAbout.isEmpty()){Color.Gray } else {Color.Blue}
                    )
                ) {
                    Text(text = "Next")

                }
            }

        }
    }
}