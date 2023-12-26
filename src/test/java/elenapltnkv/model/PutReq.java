package elenapltnkv.model;

public class PutReq {
    public static class ProviderBonus{
        public String type;
        public double value;
        public double fixValue;
    }

    public static class Root{
        public Object id;
        public String partnerId;
        public String providerId;
        public String processingId;
        public String name;
        public String externalId;
        public String email;
        public String phone;
        public int walletId;
        public int walletNumber;
        public ProviderBonus providerBonus;
    }



}
