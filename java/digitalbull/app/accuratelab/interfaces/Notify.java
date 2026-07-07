package digitalbull.app.accuratelab.interfaces;

import digitalbull.app.accuratelab.Models.ProductModel;

public interface Notify {
    void onAdd(ProductModel data);
    void onRemove(ProductModel data);
}
