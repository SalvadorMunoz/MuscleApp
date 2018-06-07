package com.example.linux.muscleapp.net;

import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
/*
$app->post('/users/filtered', function ($request, $response,$args) {
    $result = new Result();
    $input = $request->getParsedBody();
    try {
        $dbquery =  $this->db->prepare("SELECT * FROM ".TABLE_USER." WHERE id <> ? AND name like '%".$args['name']."%' ORDER BY LOWER(name)");
        $dbquery->bindParam(1,$input['id']);
        $dbquery->execute();
        $users = $dbquery->fetchAll();
        $result->setCode(TRUE);
        $result->setStatus(OK);
        $result->setUsers($users);
        $result->setMessage($args['users']);

    } catch (PDOException $e) {
        $result->setCode(FALSE);
        $result->setStatus(CONFLICT);
        $result->setMessage("Error: " . $e->getMessage());
    }
    return $this->response->withJson($result);
});
* */

public interface ApiService {
    @SerializedName("users")
    @POST("users/{email}")
    Call<Result> getUsers(@Body User current, @Path("email") String email);

    @SerializedName("currentUser")
    @GET("users/current/{email}")
    Call<Result> getCurrentUser(@Path("email") String email);

    @SerializedName("insertUser")
    @POST("user")
    Call<Result> insertUser(@Body User current);

    @SerializedName("sendConfirm")
    @POST("ass/{email}")
    Call<Result> sendConfirm(@Path("email") String email);

    @SerializedName("sendRecovery")
    @POST("recovery/{email}")
    Call<Result> sendRecovery(@Path("email") String email);

    @SerializedName("userName")
    @GET("users/name/{id}")
    Call<Result> getUserName(@Path("id") int id);

    @SerializedName("sessions")
    @GET("sessions")
    Call<Result> getSessions();

    @SerializedName("insertSession")
    @POST("session")
    Call<Result> insertSession(@Body Session current);

    @SerializedName("sessionId")
    @POST("sessions/creation/id")
    Call<Result> getSessionId(@Body Session current);

    @SerializedName("addSessionDates")
    @POST("sessionDates/add")
    Call<Result> insertSessionDates(@Body SessionDate current);
    @SerializedName("seeSessionDates")
    @GET("sessionDates/{session}")
    Call<Result> getSessionDates(@Path("session") int session);

    @SerializedName("addExcersice")
    @POST("excersices/add")
    Call<Result> insertExcersices(@Body Excersice current);

    @SerializedName("seeExcersice")
    @GET("excersices/{session}")
    Call<Result> getExcersices(@Path("session") int session);

    @SerializedName("seeCommentary")
    @GET("commentaries/{session}")
    Call<Result> getCommentaries(@Path("session") int session);

    @SerializedName("addCommentary")
    @POST("commentaries/add")
    Call<Result> insertCommentary(@Body Commentary current);

    @SerializedName("addFavourite")
    @POST("favourites/add")
    Call<Result> insertFavourite(@Body Favourite current);

    @SerializedName("favourites")
    @GET("favourites/{follower}")
    Call<Result> getFavourites(@Path("follower") int follower);

    @SerializedName("removeFavourite")
    @POST("favourites/remove")
    Call<Result> removeFavourite(@Body Favourite current);

    @SerializedName("today")
    @GET("today/{user}")
    Call<Result> getTodaySessions(@Path("user") int user);

    @SerializedName("filtered")
    @POST("c")
    Call<Result> getFilteredUsers(@Body User user);

    @SerializedName("userSessions")
    @GET("sessions/user/{id}")
    Call<Result> getUsersSessions(@Path("id") int id);

    @SerializedName("removeSession")
    @GET("session/remove/{id}")
    Call<Result> removeSession(@Path("id") int id);

    @SerializedName("allUsers")
    @GET("all/users")
    Call<Result> getAllUsers();


}

