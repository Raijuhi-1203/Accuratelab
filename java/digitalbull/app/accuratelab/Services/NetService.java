package digitalbull.app.accuratelab.Services;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.util.ArrayList;

import digitalbull.app.accuratelab.Utils.Constants;

public class NetService {

    //Method which invoke web methods
    public static String invokeJSONWS(String MEthod, ArrayList<NetParam> keyvalues) {
        String responseJSON = "";
        // Create request
        SoapObject request = new SoapObject(Constants.NAMESPACE, MEthod);
        if (keyvalues != null) {
            for (NetParam param : keyvalues) {
                PropertyInfo paramPI = new PropertyInfo();
                paramPI.setName(param.getKey());
                paramPI.setValue(param.getValue());
                request.addProperty(paramPI);
            }
        }
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(Constants.URL);
        try {
            // Invole web service
            androidHttpTransport.call(Constants.SOAP_ACTION + MEthod, envelope);
            // Get the response
             SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
          //  SoapObject response = (SoapObject) envelope.getResponse();
            // Assign it to static variable
            responseJSON = response.toString();
            // JSONObject readxml=UserUtil.readToJSON(responseJSON);

        } catch (Exception e) {
            e.printStackTrace();
        }
       return responseJSON;
    }

}
