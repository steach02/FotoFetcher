package com.tobin.fotofetcher.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tobin.fotofetcher.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends Activity {

    // implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener

    // Twitter login variables
    //This is your KEY and SECRET
    //And it would be added automatically while the configuration
    private static final String TWITTER_KEY = "kbwf3Qbb6MfjXiPirco5j3UYe";
    private static final String TWITTER_SECRET = "1Lr0HOTxQBLU22FsH2CH9RFBtnQCHwjAsidZrctvYoy9yIDX7n";

    //Tags to send the username and image url to next activity using intent
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PROFILE_IMAGE_URL = "image_url";

    //Twitter Login Button
    private TwitterLoginButton twitterLoginButton;
    // End of Twitter login variables

    // Facebook login variables
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker tokenTracker;
    private ProfileTracker profileTracker;
    private FacebookCallback<LoginResult> callback;
    // End of Facebook login variables

    // Google Plus login variables
    private static final int RC_SIGN_IN = 0;

    // Google client to communicate with Google
    private GoogleApiClient mGoogleApiClient;

    private boolean mIntentInProgress;
    private boolean signedInUser;
    private ConnectionResult mConnectionResult;
    private SignInButton signinButton;
    private ImageView image;
    private TextView username, emailLabel;
    private LinearLayout profileFrame, signinFrame;
    // End of Google Plus login variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeTwitter();
//        initializeFacebook();
        setContentView(R.layout.activity_login);
        loginTwitter();
//        loginFacebook();
//        initializeGooglePlus();
    }

    public void initializeTwitter() {
        //Initializing TwitterAuthConfig, these two line will also added automatically while configuration we did
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public void loginTwitter() {
        //Initializing twitter login button
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);

        //Adding callback to the button
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                //If login succeeds passing the Calling the login method and passing Result object
                loginTwitterInitialize(result);
            }

            @Override
            public void failure(TwitterException exception) {
                //If failure occurs while login handle it here
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

    //The login function accepting the result object
    public void loginTwitterInitialize(Result<TwitterSession> result) {

        //Creating a twitter session with result's data
        TwitterSession session = result.data;

        //Getting the username from session
        final String username = session.getUserName();

        //This code will fetch the profile image URL
        //Getting the account service of the user logged in
        Twitter.getApiClient(session).getAccountService()
                .verifyCredentials(true, false, new Callback<User>() {
                    @Override
                    public void failure(TwitterException e) {
                        //If any error occurs handle it here
                    }

                    @Override
                    public void success(Result<User> userResult) {
                        //If it succeeds creating a User object from userResult.data
                        User user = userResult.data;

                        //Getting the profile image url
                        String profileImage = user.profileImageUrl.replace("_normal", "");

                        //Creating an Intent
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);


                        //Adding the values to intent
                        intent.putExtra(KEY_USERNAME, username);
                        intent.putExtra(KEY_PROFILE_IMAGE_URL, profileImage);

                        //Starting intent
                        startActivity(intent);
                    }
                });
    }

//    public void initializeFacebook() {
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        callbackManager = CallbackManager.Factory.create();
//        tokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
//
//            }
//        };
//        profileTracker = new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
//
//            }
//        };
//        tokenTracker.startTracking();
//        profileTracker.startTracking();
//    }


//    public void loginFacebook() {
//        loginButton = (LoginButton) findViewById(R.id.login_button);
//        callback = new FacebookCallback<LoginResult>() {
//            boolean isMainLobbyStarted = false;
//            AccessToken accessToken;
//            Profile profile;
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                accessToken = loginResult.getAccessToken();
//                profile = Profile.getCurrentProfile();
//
////                Intent homeFBIntent = new Intent(LoginActivity.this, HomeActivity.class);
////                if(!isMainLobbyStarted) {
////                    //homeFBIntent.putExtra(KEY_USERNAME, profile.getName());
////                    startActivity(homeFBIntent);
////                    isMainLobbyStarted = true;
////                }
//
//                Log.d("Success", "success");
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        };
//        loginButton.setReadPermissions("user_friends");
//        loginButton.registerCallback(callbackManager, callback);
//    }

//    public void initializeGooglePlus() {
//        signinButton = (SignInButton) findViewById(R.id.signin);
//        signinButton.setOnClickListener(this);
//
//        image = (ImageView) findViewById(R.id.image);
//        username = (TextView) findViewById(R.id.username);
//        emailLabel = (TextView) findViewById(R.id.email);
//
//        profileFrame = (LinearLayout) findViewById(R.id.profileFrame);
//        signinFrame = (LinearLayout) findViewById(R.id.signinFrame);
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Plus.API, Plus.PlusOptions.builder().build()).addScope(Plus.SCOPE_PLUS_LOGIN).build();
//    }

    protected void onStart() {
        super.onStart();
        //mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
//        tokenTracker.stopTracking();
//        profileTracker.stopTracking();
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
    }

//    private void resolveSignInError() {
//        if (mConnectionResult.hasResolution()) {
//            try {
//                mIntentInProgress = true;
//                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
//            } catch (IntentSender.SendIntentException e) {
//                mIntentInProgress = false;
//                mGoogleApiClient.connect();
//            }
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        if (!result.hasResolution()) {
//            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
//            return;
//        }
//
//        if (!mIntentInProgress) {
//            // store mConnectionResult
//            mConnectionResult = result;
//
//            if (signedInUser) {
//                resolveSignInError();
//            }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case RC_SIGN_IN:
//                if (resultCode == RESULT_OK) {
//                    signedInUser = false;
//
//                }
//                mIntentInProgress = false;
//                if (!mGoogleApiClient.isConnecting()) {
//                    mGoogleApiClient.connect();
//                }
//                break;
//        }
    }

    // For Development purposes we need to comment out the Twitter login implementation
    public void login(View view) {
        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeIntent);
    }

//    @Override
//    public void onConnected(Bundle arg0) {
//        signedInUser = false;
//        Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
//        getProfileInformation();
//    }
//
//    private void updateProfile(boolean isSignedIn) {
//        if (isSignedIn) {
//            signinFrame.setVisibility(View.GONE);
//            profileFrame.setVisibility(View.VISIBLE);
//
//        } else {
//            signinFrame.setVisibility(View.VISIBLE);
//            profileFrame.setVisibility(View.GONE);
//        }
//    }
//
//    private void getProfileInformation() {
//        try {
//            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
//                Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
//                String personName = currentPerson.getDisplayName();
//                String personPhotoUrl = currentPerson.getImage().getUrl();
//                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
//
//                username.setText(personName);
//                emailLabel.setText(email);
//
//                new LoadProfileImage(image).execute(personPhotoUrl);
//
//                // update profile frame with new info about Google Account
//                // profile
//                updateProfile(true);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onConnectionSuspended(int cause) {
//        mGoogleApiClient.connect();
//        updateProfile(false);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.signin:
//                googlePlusLogin();
//                break;
//        }
//    }
//
//    public void signIn(View v) {
//        googlePlusLogin();
//    }
//
//    public void logout(View v) {
//        googlePlusLogout();
//    }
//
//    private void googlePlusLogin() {
//        if (!mGoogleApiClient.isConnecting()) {
//            signedInUser = true;
//            resolveSignInError();
//        }
//    }
//
//    private void googlePlusLogout() {
//        if (mGoogleApiClient.isConnected()) {
//            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
//            mGoogleApiClient.disconnect();
//            mGoogleApiClient.connect();
//            updateProfile(false);
//        }
//    }
//
//    // download Google Account profile image, to complete profile
//    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
//        ImageView downloadedImage;
//
//        public LoadProfileImage(ImageView image) {
//            this.downloadedImage = image;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String url = urls[0];
//            Bitmap icon = null;
//            try {
//                InputStream in = new java.net.URL(url).openStream();
//                icon = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return icon;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            downloadedImage.setImageBitmap(result);
//        }
//    }

}
