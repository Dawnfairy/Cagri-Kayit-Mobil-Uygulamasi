package com.ft.callrecording;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CallRecordingManager {


    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;
    private String outputFilePath;


    public CallRecordingManager(Context context) {

    }

    public void startRecording() {
        if (!isRecording) {

            String kayitDizini = String.valueOf(Environment.getExternalStorageDirectory());
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            outputFilePath = kayitDizini + File.separator + "seskaydi" + timeStamp + ".mp3";

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(outputFilePath);
            System.out.println(outputFilePath);
            try {

                mediaRecorder.prepare();
                mediaRecorder.start();
                isRecording = true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRecording() {
        if (isRecording && mediaRecorder != null) {
            System.out.println("kayıt bitti");
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
        }


        //firebase storage e kaydedilen dosyayı yükleme

/*
// Firebase Storage referansını alın
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

// Yüklemek istediğiniz dosyanın adını ve yolunu belirleyin
        String dosyaYolu = outputFilePath;
        StorageReference dosyaReferansi = storageRef.child("kayitlar/call_reco.mp3");

// MP3 dosyasını yüklemek için dosyanın yolunu belirtin

        Uri dosyaUri = Uri.fromFile(new File(dosyaYolu));

        UploadTask uploadTask = dosyaReferansi.putFile(dosyaUri);

        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Yükleme başarılı oldu
            // Dosyanın indirme URL'sini alabilirsiniz
            dosyaReferansi.getDownloadUrl().addOnSuccessListener(uri -> {
                String downloadUrl = uri.toString();
                // İndirme URL'sini kullanarak veritabanında veya başka bir yerde saklayabilirsiniz


            });
        }).addOnFailureListener(exception -> {
            // Yükleme başarısız oldu
            // Hata mesajını kontrol edebilirsiniz
        });
    }

    */


    }
}