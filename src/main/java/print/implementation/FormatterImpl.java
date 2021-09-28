package print.implementation;

import i18n.Messenger;
import print.api.Formatter;

import java.util.Collection;

public class FormatterImpl implements Formatter {

    public String formatCollection(Collection<?> collection) {
        return collection
                .toString()
                .substring(1, collection.toString().length()-1)
                .replaceAll(", ", "\n");
    }

    public String formatBooleanOperation(boolean bool, Messenger messenger) {
        if(bool) {
            return messenger.getMessage("booleanOpTrue");
        } else {
            return messenger.getMessage("booleanOpFalse");
        }
    }
}
