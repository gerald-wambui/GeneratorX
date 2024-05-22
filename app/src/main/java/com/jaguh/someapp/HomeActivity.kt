package com.jaguh.someapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.jaguh.someapp.navigation.AI
import com.jaguh.someapp.navigation.Navigations
import com.jaguh.someapp.navigation.Step2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

val uriState = MutableStateFlow("")

class HomeActivity : ComponentActivity() {

    val imagePicker =
        registerForActivityResult(
            ActivityResultContracts.PickVisualMedia()
        ) {uri ->
            uri?.let {
                uriState.update { uri.toString() }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigations()
        }
    }
}




@Composable
fun StartScreen(navController: NavController) {

    val context = LocalContext.current
    val brush = remember { Brush.linearGradient(colors = getGradientColors()) }
    val textStyle = TextStyle(
        textAlign = TextAlign.Justify,
        lineHeight = 20.sp,
        textIndent = TextIndent(firstLine = 14.sp, restLine = 3.sp),
        brush = brush
    )

    Scaffold(
        floatingActionButton = {
            Button(
                onClick = {
                          navController.navigate(AI.route)
                },
                modifier = Modifier,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors( Color.Blue)
            ){
                Text(text = "Help?")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            Column(
                Modifier.padding(start = 16.dp, end = 16.dp, top = 160.dp)
            ) {
                Text(
                    text = "Let's start creating",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
                Row {
                    Text(
                        text = "your",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                    Text(
                        text = "Website with AI",
                        style = textStyle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))


                var mExpanded by remember { mutableStateOf(false) }

                val mCities =
                    listOf(
                        "Counseling services",
                        "Plumbing",
                        "Online Computer Accessories Store",
                        "Dental clinic",
                        "Digital marketing agency",
                        "Bengaluru",
                        "Dental clinic",
                        "E-Commerce",
                        "Digital marketing agency"
                    )

                // Create a string value to store the selected city
                var mSelectedText by remember { mutableStateOf("") }

                var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

                // Up Icon when expanded and down icon when collapsed
                val icon = if (mExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown


                Column(Modifier.padding(20.dp)) {

                    Text(text = "Please select the type of your business")
                    Spacer(modifier = Modifier.height(1.dp))
                    // Create an Outlined Text Field
                    // with icon and not expanded
                    OutlinedTextField(
                        value = mSelectedText,
                        onValueChange = { mSelectedText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                // This value is used to assign to
                                // the DropDown the same width
                                mTextFieldSize = coordinates.size.toSize()
                            },
                        label = {
                            Text(
                                text = "Select your business or site type",
                                color = Color.Gray
                            )
                        },
                        trailingIcon = {
                            Icon(icon, "contentDescription",
                                Modifier.clickable { mExpanded = !mExpanded })
                        }
                    )

                    DropdownMenu(
                        expanded = mExpanded,
                        onDismissRequest = { mExpanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                    ) {
                        mCities.forEach { label ->
                            DropdownMenuItem(onClick = {
                                mSelectedText = label
                                mExpanded = false
                            }, text = { Text(text = label) })

                        }
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalAlignment = Alignment.End
                ) {

                    Button(
                        onClick = {
                            if (mSelectedText.isNotEmpty())
                                  navController.navigate(Step2.route)
                            else
                                Toast.makeText(context, "Please select the type of your business", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            if (mSelectedText.isEmpty()) Color.Gray else Color.Blue
                        )
                    ) {
                        Text(text = "Next")

                    }
                }
            }

        }
    }
}

fun getGradientColors(): List<Color> {
        return listOf(Color.Blue, Color.Green,)
}

@Composable
fun TextWithStyle(previewText: String, brush: Brush, modifier: Modifier = Modifier) {
        val textStyle = TextStyle(
            textAlign = TextAlign.Justify,
            lineHeight = 20.sp,
            textIndent = TextIndent(firstLine = 14.sp, restLine = 3.sp),
            brush = brush
        )

        Text(
            text = previewText,
            modifier = modifier.padding(16.dp),
            style = textStyle
        )
}
