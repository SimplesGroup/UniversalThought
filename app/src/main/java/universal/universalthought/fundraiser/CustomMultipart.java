package universal.universalthought.fundraiser;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.entity.mime.MultipartEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kuppusamy on 4/11/2018.
 */

public class CustomMultipart extends Request<String> {
private Response.Listener mlistener;
private Response.ErrorListener errorListener;
private Map<String,String>header;
private MultipartEntity entity;
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String ,String>header=new HashMap<>();
        return header;
    }
public CustomMultipart(String url, Response.Listener listener, Response.ErrorListener errorListeners, MultipartEntity entity){
        super(Method.POST,url,errorListeners);
        mlistener=listener;
        errorListener=errorListeners;
        this.entity=entity;

}

    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        }catch (IOException e){
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
       try {
           if (response.data.length == 0) {
            byte [] responsedata="{}".getBytes("UTF8");
                response=new NetworkResponse(response.statusCode,responsedata,response.headers,response.notModified);
           }
           String jsondata=new String(response.data, HttpHeaderParser.parseCharset(response.headers));
           return Response.success(jsondata,HttpHeaderParser.parseCacheHeaders(response));
       }catch (UnsupportedEncodingException e){
           System.out.println("VolleyQueue: Encoding Error for " + getTag()
                   + " (" + getSequence() + ")");
           return Response.error(new ParseError(e));
       }
    }

    @Override
    protected void deliverResponse(String response) {
        Log.d("DEBUG::Volley", response.toString());
        System.out.println("VolleyQueue: Response Delivered for " + getTag()
                + " (" + getSequence() + ")");
        mlistener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }
}
