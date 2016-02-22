package net.chris.api.model.output;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.williamhill.trading.rnd.frameworks.model.api.market.Selection;
import com.williamhill.trading.rnd.frameworks.model.api.market.Selections;
import com.williamhill.trading.rnd.frameworks.model.domain.template.market.SelectionTemplate;

public class OutboundSelections {

    public static OutboundSelection[] from(Selections selections, Map<String, SelectionTemplate> templates) {
        List<OutboundSelection> outboundSelections = new ArrayList<OutboundSelection>();
        for (Selection selection : selections) {
            OutboundSelection outboundSelection = OutboundSelection.from(selection, templates.get(selection.getName()));
            outboundSelections.add(outboundSelection);
        }
        return outboundSelections.toArray(new OutboundSelection[outboundSelections.size()]);
    }
}
