package be.kuleuven.queazy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import be.kuleuven.queazy.R;
import be.kuleuven.queazy.interfaces.BackBtn;
import be.kuleuven.queazy.models.CurrentUser;

public class ProfileActivity extends AppCompatActivity implements BackBtn {

    private RequestQueue requestQueue;
    private TextView username;
    private int totalpoints;
    private int ranking;
    private String un;
    private int quizzesPassed;
    private TextView txtQuizzesPassedValue;
    private String b64String;

    private TextView txtPointsValue;
    private TextView txtRankValue;
    private ImageView ivAvatar;
    private Button btnSaveAvatar;
    private final int PICK_IMAGE_REQUEST = 111;

    private Bitmap bitmap;
    private ProgressDialog progressDialog;

    private static final String POST_URL = "https://studev.groept.be/api/a21pt216/insertImage";
    private static final String GET_IMAGE_URL = "https://studev.groept.be/api/a21pt216/getImage/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = findViewById(R.id.txtUsername3);
        txtQuizzesPassedValue = findViewById(R.id.txtQuizzesPassedValue);
        txtPointsValue = findViewById(R.id.txtPointsValue);
        txtRankValue = findViewById(R.id.txtRankValue);
        ivAvatar = findViewById(R.id.ivAvatar);
        btnSaveAvatar = findViewById(R.id.btnSaveAvatar);
        btnSaveAvatar.setEnabled(false);
        btnSaveAvatar.setVisibility(View.INVISIBLE);

        username.setText(CurrentUser.getCurrentUser());
        orderUsers();
        putImage();

    }


    @Override
    public void onBackBtnClicked(View caller) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void putImage() {
        String imageURL = GET_IMAGE_URL + username.getText().toString();
        //Standard Volley request. We don't need any parameters for this one
        JsonArrayRequest retrieveImageRequest = new JsonArrayRequest(Request.Method.GET, imageURL, null,
                response -> {
                    try {
                        if (response.length() != 0) {
                            JSONObject o = response.getJSONObject(response.length() - 1);
                            //converting base64 string to image
                            b64String = o.getString("image");
                        } else {
                            b64String = "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8H4FACBBwauHioeNSTuBdbFTYZtZ60gnIsWmmekJRa/q6VnyE1FQHRadEPn8RrCVfwDS4OC3PUamTtuAPFT96ApEslILeuw9mogTv8g0KEQASrSz9o79vBSzlvh3Bv0ahyEuerQJph4Hdx2zksnIbVCY+WdGdaJgD+pac+I81uTinowrcO5mbqe79Kx6xNCtyN2375mGj+sDRKXCSNAdsukXyi+NqxUxB2GBo5GLJ8T+MqFWGF3tTo4XMSryZSQ9m+tN9GtwHz1c+3//2gAMAwEAAgADAAAAEAggggggggggggggggggggggggggggggggggggigggggggggggglrrPogggggggggklPPPDAggggggggtvPPPPIggggggggr/PPPPIgggggggghtPPPOAggggggggkn+PMAwggggggggggljgggggggggggiv3jC+ygggggggl9XPPPLIQggggggl/PPPPPPFQggggnnPPPPPPPOKggggn/PPPPPPPPCwgv/xAAdEQEAAgICAwAAAAAAAAAAAAABETAAIBBAITFQ/9oACAEDAQE/EPiw2ho+L2ovNmk6A6L2TI5jIp95GyUFDsXlTz//xAAaEQACAgMAAAAAAAAAAAAAAAABERBAMFBg/9oACAECAQE/ENm4fTuHZN05f//EACoQAQABAgQGAgICAwAAAAAAAAERACExQVFhMEBxgZGhscEg8BDxcNHh/9oACAEBAAE/EP8AEScDui8zQ5rkge6CrePVfuo4HOKKeGrTCCZvhQiCMjg68uVJWW5LQ/opWUTMhsDAOlT/ACNJ7nu0G7FdKY2pLNXU00TlkV6uNOVyXo60irr+aFFXOoDMaWaC5tzxX1tykWKXPPt3GldOJzVl4MTZktv1h602DR5N4Vsfy+1PBe8EDETBo5MmgZGw8jyQS0yCWyJe9rw0dSqDQYe55KOPCkUS+XfDRew41RHJP6xBVnJKffD/AHqjU48ixhKM8qOzI98NRy0zvQ48iBi4JFAWjEOy4bK9wCR5IMXp2SAGSwH4e9PBimTPG4yI9U9+TnqnpTk7JZppuyYROAE0IItiB4iauehRwXAIALAcoFWhigdAZNmjx4g0BvjO00rDMUo7NRpUqlpUNIASJd7nA81j669KMDsmhuXBID/bvy7FMIQ/lQBeZxPisU7IfdYWvVfulUlgZB3iixTwCA5m+RUOj4reeK3FQ6cwWE5WAbrhTdtZAZ9dvE1kg5L7YPVNA7kj0UzLX9c6uI9g8NOQ1lOdyGmmJ5kftvRwtf2rHtNImJHJguBNZS0b32s9j1SCepd24+WlJ+U0+VpRCOzQipBFh9vRqBk2ayaZHxWfILuchABirQhFLKax8zFplLjwppwHYlhoYI6VcA0Vg3MTbE4/WnXSK4OTb7pZeKAkDcKqwpAOwcANHMyeKCsGNQC5RN1yO5260lz49yxt1s5bJWLQDK+cbjxF2hkdWQ3WCp0yLpOAbBbtyBWWYArZbtC3imynDnu0w3qN7fHJMRMJiIyNZciOgPk98KBLAuuhSy5uhMfUHJDFYuhN0bHmXfhPex2YuIe2pLfHk0W7jpI9hTi8GXcdRD/yVYuTa+GB7aEdT8f/2Q==";
                        }
                        byte[] imageBytes = Base64.decode(b64String, Base64.DEFAULT);
                        Bitmap bitmap2 = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        //Link the bitmap to the ImageView, so it's visible on screen
                        ivAvatar.setImageBitmap(bitmap2);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> Toast.makeText(ProfileActivity.this, "Unable to communicate with server", Toast.LENGTH_LONG).show()
        );

        requestQueue.add(retrieveImageRequest);
    }
    public void orderUsers() {
        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/orderUsersByPoints";
        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject o = response.getJSONObject(i);
                            ranking = i + 1;
                            un = o.getString("username");
                            quizzesPassed = o.getInt("quizzespassed");
                            totalpoints = o.getInt("totalpoints");

                            if (un.equals(CurrentUser.getCurrentUser())) {
                                this.txtRankValue.setText(String.valueOf(ranking));
                                this.txtQuizzesPassedValue.setText(String.valueOf(quizzesPassed));
                                this.txtPointsValue.setText(String.valueOf(totalpoints));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(ProfileActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }

    public void onBtnLogOut_Clicked(View caller){
        CurrentUser.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Starts a new (automatic) activity to select an image from your phone
     */
    public void onFabChangeAvatar_Clicked(View caller)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);

        //this line will start the new activity and will automatically run the callback method below when the user has picked an image
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

        btnSaveAvatar.setVisibility(View.VISIBLE);
        btnSaveAvatar.setEnabled(true);
    }

    /**
     * Processes the image picked by the user. For now, the bitmap is simply stored in an attribute.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Rescale the bitmap to 400px wide (avoid storing large images!)
                bitmap = getResizedBitmap( bitmap, 400 );

                //Setting image to ImageView
                ivAvatar.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Submits a new image to the database
     */
    public void onBtnSaveAvatarClicked(View caller)
    {
        //Start an animating progress widget
        progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();

        //convert image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        //Execute the Volley call. Note that we are not appending the image string to the URL, that happens further below
        StringRequest submitRequest = new StringRequest (Request.Method.POST, POST_URL, response -> {
            //Turn the progress widget off
            progressDialog.dismiss();
            Toast.makeText(ProfileActivity.this, "Post request executed", Toast.LENGTH_SHORT).show();
        }, error -> Toast.makeText(ProfileActivity.this, "Post request failed", Toast.LENGTH_LONG).show()) { //NOTE THIS PART: here we are passing the parameter to the webservice, NOT in the URL!
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("image", imageString);
                params.put("username", CurrentUser.getCurrentUser());
                return params;
            }
        };

        requestQueue.add(submitRequest);
        btnSaveAvatar.setEnabled(false);
        btnSaveAvatar.setVisibility(View.INVISIBLE);
    }



    /**
     * Helper method to create a rescaled bitmap. You enter a desired width, and the height is scaled uniformly
     */
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scale = ((float) newWidth) / width;

        // We create a matrix to transform the image
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create the new bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}