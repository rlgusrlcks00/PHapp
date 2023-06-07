package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OpenAIAPI {
    @Headers("Authorization: Bearer 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")
    @POST("v1/engines/davinci-codex/completions")
    Call<CompletionResponse> createCompletion(@Body CompletionRequest request);
}
