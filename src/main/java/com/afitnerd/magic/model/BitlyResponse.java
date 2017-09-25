package com.afitnerd.magic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BitlyResponse {

    @JsonProperty("data")
    private BitlyData bitlyData;

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("status_text")
    private String statusText;

    public BitlyData getBitlyData() {
        return bitlyData;
    }

    public void setBitlyData(BitlyData bitlyData) {
        this.bitlyData = bitlyData;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public class BitlyData {

        @JsonProperty("global_hash")
        private String globalHash;

        @JsonProperty("long_url")
        private String longUrl;

        @JsonProperty("new_hash")
        private int newHash;

        private String hash;
        private String url;

        public String getGlobalHash() {
            return globalHash;
        }

        public void setGlobalHash(String globalHash) {
            this.globalHash = globalHash;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getLongUrl() {
            return longUrl;
        }

        public void setLongUrl(String longUrl) {
            this.longUrl = longUrl;
        }

        public int getNewHash() {
            return newHash;
        }

        public void setNewHash(int newHash) {
            this.newHash = newHash;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
