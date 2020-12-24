package com.amazaar.Widget.UploadImageWIdget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.amazaar.CommonCode.AToast;
import com.amazaar.CommonCode.DataModel;
import com.amazaar.DefaultProviders.ImagePbDefaultProvider;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import javax.inject.Inject;

public class UploadView {

    private final int PICK_IMAGE_REQUEST = 71;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Uri m_filePath;
    private DataModel<ImagePbOuterClass.ImagePb, ImagePbDefaultProvider> m_imageModel;


    @Inject
    public UploadView(DataModel<ImagePbOuterClass.ImagePb, ImagePbDefaultProvider> imageModel) {
        m_imageModel=imageModel;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    public int getPICK_IMAGE_REQUEST() {
        return PICK_IMAGE_REQUEST;
    }

    public Uri getFilePath() {
        return m_filePath;
    }

    public void setFilePath(Uri filePath) {
        m_filePath = filePath;
    }

    public DataModel<ImagePbOuterClass.ImagePb, ImagePbDefaultProvider> getImageModel(){
        return m_imageModel;
    }

    public void chooseImage(Context context) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        Activity activity = (Activity) context;
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void uploadImage(Context context) {

        if (m_filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/DEFAULT_IMAGE/" + UUID.randomUUID().toString());
            StorageTask<UploadTask.TaskSnapshot> uploadTask = ref.putFile(m_filePath);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        progressDialog.dismiss();
                        throw task.getException();
                    } else {
                        progressDialog.setMessage("Please wait...");
                    }
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        Log.e("url", downloadUri.toString());
                        AToast.uploadToast();
                        progressDialog.dismiss();
                    } else {
                        AToast.failedToast();
                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    progressDialog.dismiss();
                }
            });
        }
    }
}
