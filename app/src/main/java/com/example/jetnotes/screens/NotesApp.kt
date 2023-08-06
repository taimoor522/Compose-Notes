package com.example.jetnotes.screens

import ButtonWidget
import OutlinedCardWidget
import TextFieldWidget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnotes.data.model.Note
import com.example.jetnotes.data.repository.NoteRepository
import com.example.jetnotes.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NotesApp(noteViewModel: NoteViewModel) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopAppBar(title = { Text(text = "Notes App") })
            TextFieldWidget(
                text = title,
                label = "Title",
            ) { title = it }
            TextFieldWidget(
                text = body,
                label = "Body",
                singleLine = false,
            ) { body = it }
            ButtonWidget(
                text = "Save",
                modifier = Modifier.fillMaxWidth()
            ) {
                title  = ""
                body = ""
                keyboardController?.hide()
                focusManager.clearFocus()
                noteViewModel.addNote(Note(title = title, body = body))
            }
            NotesList(noteViewModel.notes.collectAsState().value)
        }
    }
}


@Composable
fun NotesList(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(notes.size) { index ->
            Card(
                modifier = Modifier.padding(bottom = 10.dp)

            ) {
                OutlinedCardWidget(
                    title = notes[index].title,
                    subtitle = notes[index].body,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
