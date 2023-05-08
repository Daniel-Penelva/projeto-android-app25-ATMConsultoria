package com.example.atmconsultoria.ui.sobre;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atmconsultoria.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFragment extends Fragment {


    public SobreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String descricao = "ATM consultoria tem como missão apoiar organizações " +
                "que desejam alcançar o sucesso através da excelência em gestão e " +
                "da busca pela qualidade";

        Element versao = new Element();
        versao.setTitle("Versão 1.0");

        return new AboutPage(getActivity())
                .setImage(R.drawable.logo)
                .setDescription(descricao)

                .addGroup("Entre em contato")
                .addEmail("atendimento@atmconsultoria.com.br", "Envia um e-mail")
                .addWebsite("https://www.google.com/","Acesse nosso site")

                .addGroup("Redes Socias")
                .addFacebook("danielpenelva", "Facebook")
                .addInstagram("danielpenelva", "Instagram")
                .addTwitter("danielpenelva", "Twitter")
                .addGitHub("Daniel-Penelva", "GitHub")
                .addItem(versao)
                .create();


       // Dependencia About Page
       // https://github.com/medyo/android-about-page
    }

}
