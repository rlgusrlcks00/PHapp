package com.example.myapplication;

public class CompletionRequest {
    String prompt;
    int max_tokens;

    // 생성자
    public CompletionRequest(String prompt, int max_tokens) {
        this.prompt = prompt;
        this.max_tokens = max_tokens;
    }

    // getter, setter
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getMaxTokens() {
        return max_tokens;
    }

    public void setMaxTokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }
}
