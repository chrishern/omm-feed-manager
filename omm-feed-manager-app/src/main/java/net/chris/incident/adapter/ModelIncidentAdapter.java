package net.chris.incident.adapter;


import net.chris.api.model.input.Incident;

public final class ModelIncidentAdapter {

    public static Incident fromCaerusIncident(final net.chris.api.caerus.output.Incident caerusIncident) {

        final Incident modelIncident = new Incident();

        modelIncident.setGameSeconds(caerusIncident.getGameSeconds());
        modelIncident.setIncidentType(caerusIncident.getType());
        modelIncident.setPeriod(toModelPeriod(caerusIncident.getPeriod()));

        return modelIncident;
    }

    private static String toModelPeriod(final String caerusPeriod) {
        if (caerusPeriod.equals("PRE_GAME")) {
            return "PRE_MATCH";
        }

        return caerusPeriod;
    }
}
