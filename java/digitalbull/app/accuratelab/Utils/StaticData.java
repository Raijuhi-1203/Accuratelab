package digitalbull.app.accuratelab.Utils;


import java.util.ArrayList;
import java.util.List;

public class StaticData {
    public static List<TempModel> data = new ArrayList<>();

    public static List<TempModel> GetData() {
        return data;
    }

    public static boolean AddData(TempModel model) {
        boolean isexist = false;
        for (TempModel u : data) {
            if (u.getCSTID() == model.getCSTID()) {
                isexist = true;
            }
        }
        if (!isexist) {
            data.add(model);
            return true;
        } else {
            return false;
        }
    }

    public static void RemoveData(int position) {
        data.remove(position);
    }
}
