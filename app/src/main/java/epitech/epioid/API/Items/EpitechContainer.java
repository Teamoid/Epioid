package epitech.epioid.API.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michelantoine on 17/01/15.
 */
public abstract class EpitechContainer extends EpitechItem {
    public List<EpitechItem> items = new ArrayList<>();

    public void addItem(EpitechItem item) {
        items.add(item);
    }
}
