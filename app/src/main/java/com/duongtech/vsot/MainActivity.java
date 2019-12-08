package com.duongtech.vsot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.duongtech.vsot.VSOT.VSOT;

public class MainActivity extends AppCompatActivity {

    EditText edtInputData;
    Spinner spnListSpeech;
    Button btnSpeak, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtInputData = findViewById(R.id.edtInputText);
        spnListSpeech = findViewById(R.id.spnListSpeech);
        btnSpeak = findViewById(R.id.btnSpeak);
        btnStop = findViewById(R.id.btnStop);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.list_speech,
                android.R.layout.simple_spinner_item
        );
        spnListSpeech.setAdapter(adapter);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lang = getLanguage(spnListSpeech.getSelectedItemPosition());
                String text = edtInputData.getText().toString();
                if (lang != null && !text.equals("")){
                    VSOT.speak(text, lang);
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VSOT.isSpeaking()){
                    VSOT.stop();
                }
            }
        });
    }

    private String getLanguage(int position) {
        String id = null;

        switch (position){
            case 0:
                id = VSOT.AFRIKAANS;
                break;
            case 1:
                id = VSOT.ALBANIAN;
                break;
            case 2:
                id = VSOT.ARABIC;
                break;
            case 3:
                id = VSOT.ARMENIAN;
                break;
            case 4:
                id = VSOT.BENGALI_BANGLADESH;
                break;
            case 5:
                id = VSOT.BENGALI_INDIA;
                break;
            case 6:
                id = VSOT.BOSNIAN;
                break;
            case 7:
                id = VSOT.BURMESE_MYANMAR;
                break;
            case 8:
                id = VSOT.CATALAN;
                break;
            case 9:
                id = VSOT.CHINESE;
                break;
            case 10:
                id = VSOT.CROATIAN;
                break;
            case 11:
                id = VSOT.CZECH;
                break;
            case 12:
                id = VSOT.DANISH;
                break;
            case 13:
                id = VSOT.DUTCH;
                break;
            case 14:
                id = VSOT.ENGLISH_AUSTRALIA;
                break;
            case 15:
                id = VSOT.ENGLISH_UK;
                break;
            case 16:
                id = VSOT.ENGLISH_US;
                break;
            case 17:
                id = VSOT.ESPERANTO;
                break;
            case 18:
                id = VSOT.FILIPINO;
                break;
            case 19:
                id = VSOT.FINNISH;
                break;
            case 20:
                id = VSOT.FRENCH;
                break;
            case 21:
                id = VSOT.FRENCH_CANADA;
                break;
            case 22:
                id = VSOT.GERMAN;
                break;
            case 23:
                id = VSOT.GREEK;
                break;
            case 24:
                id = VSOT.HINDI;
                break;
            case 25:
                id = VSOT.HUNGARIAN;
                break;
            case 26:
                id = VSOT.ICELANDIC;
                break;
            case 27:
                id = VSOT.INDONESIAN;
                break;
            case 28:
                id = VSOT.ITALIAN;
                break;
            case 29:
                id = VSOT.JAPANESE;
                break;
            case 30:
                id = VSOT.KHMER;
                break;
            case 31:
                id = VSOT.KOREAN;
                break;
            case 32:
                id = VSOT.LATIN;
                break;
            case 33:
                id = VSOT.LATVIAN;
                break;
            case 34:
                id = VSOT.MACEDONIAN;
                break;
            case 35:
                id = VSOT.NEPALI;
                break;
            case 36:
                id = VSOT.NORWEGIAN;
                break;
            case 37:
                id = VSOT.POLISH;
                break;
            case 38:
                id = VSOT.PORTUGUESE;
                break;
            case 39:
                id = VSOT.ROMANIAN;
                break;
            case 40:
                id = VSOT.RUSSIAN;
                break;
            case 41:
                id = VSOT.SERBIAN;
                break;
            case 42:
                id = VSOT.SINHALA;
                break;
            case 43:
                id = VSOT.SLOVAK;
                break;
            case 44:
                id = VSOT.SPANISH_MEXICO;
                break;
            case 45:
                id = VSOT.SPANISH_SPAIN;
                break;
            case 46:
                id = VSOT.SWAHILI;
                break;
            case 47:
                id = VSOT.SWEDISH;
                break;
            case 48:
                id = VSOT.TAMIL;
                break;
            case 49:
                id = VSOT.TELUGU;
                break;
            case 50:
                id = VSOT.THAI;
                break;
            case 51:
                id = VSOT.TURKISH;
                break;
            case 52:
                id = VSOT.UKRAINIAN;
                break;
            case 53:
                id = VSOT.VIETNAMESE;
                break;
            case 54:
                id = VSOT.WELSH;
                break;
        }

        return id;
    }
}
