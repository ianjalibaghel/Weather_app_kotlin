package com.example.todoappwithroomdb

import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoappwithroomdb.ui.theme.Pink40
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeUi(viewModel: todoViewModel) {
    val mylist by viewModel.todo.observeAsState()
    var inputtext by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(0.dp, 40.dp)) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly){
            OutlinedTextField(value =inputtext , onValueChange ={
                inputtext=it
            } )
            
            Button(modifier=Modifier.size(70.dp),onClick = {
                viewModel.addtodo(inputtext)
                inputtext=""
            }) {
                Text(text = "Add")
            }
        }
        //.let will to work the lazy column if it is not null
        mylist?.let{
            LazyColumn {
                itemsIndexed(it, itemContent = {
                        index, item ->
                    TodoItem(item = item, onDelete = {
                        viewModel.deletetodo(item.id)
                    })
                })
            }
        }?:
            Text(text ="No items yet",
                modifier = Modifier.fillMaxWidth(),
                textAlign=TextAlign.Center,
                fontSize= 16.sp )


    }
}

@Composable
fun TodoItem(item: tododata , onDelete:()-> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(color = Pink40)
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column (modifier = Modifier
            .weight(1f)
            .padding(4.dp)){
            Text(text = SimpleDateFormat("HH:mm:aa dd/mm/yyyy", Locale.ENGLISH).format(item.createdAt), style = TextStyle( fontSize = 12.sp))
            Text(text = item.title, modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
        }
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription ="Delete" )
        }
    }
}

