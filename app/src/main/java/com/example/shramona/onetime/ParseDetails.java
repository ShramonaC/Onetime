package com.example.shramona.onetime;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class ParseDetails {
    public static String[] coname;
    public static String[] csec;
    public static String[] cscor;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_CNAME = "name";
    public static final String KEY_CSECTOR = "sector";
    public static final String KEY_CSCORE = "score";

    private JSONArray company = null;

    private String json;

    public ParseDetails(String json){
        this.json = json;
    }

    protected void parseDetails(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            company = jsonObject.getJSONArray(JSON_ARRAY);

            coname= new String[company.length()];
            csec = new String[company.length()];
            cscor = new String[company.length()];

            for(int i=0;i<company.length();i++){
                JSONObject jo = company.getJSONObject(i);
                coname[i] = jo.getString(KEY_CNAME);
                cscor[i] = jo.getString(KEY_CSCORE);
                csec[i] = jo.getString(KEY_CSECTOR);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

