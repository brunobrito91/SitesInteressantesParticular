package br.edu.ifspsaocarlos.sitesinteressantes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import br.edu.ifspsaocarlos.sitesinteressantes.model.Site;

/**
 * Created by manutencao.sca on 14/09/2016.
 */
public class NovoSiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_site);
    }

    public void onClickSalvar(View view){
        if (view.getId()== R.id.btnSalvarNovoSite){
            Site site = new Site();

            EditText editText = (EditText) findViewById(R.id.editTextUrl);
            site.setUrl(editText.getText().toString());

            CheckBox checkBox = (CheckBox) findViewById(R.id.checkboxFavorito);
            if (checkBox.isChecked()){
                site.setIdImagemFavorito(R.mipmap.icone_favorito_on);
            }else {
                site.setIdImagemFavorito(R.mipmap.icone_favorito_off);
            }

            Intent intent = new Intent();
            intent.putExtra("novoSite", site);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(RESULT_CANCELED);
    }
}
