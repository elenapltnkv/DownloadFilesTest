package elenapltnkv.model;

public class Put_req {
    public class CommissionFromRecipient{
        public String type;
        public double value;
        public double fixValue;
    }

    public class PartnerBonus{
        public String type;
        public double value;
        public double fixValue;
    }

    public class ProcessingParams{
        public String xs2Password;
        public boolean checkSign;
        public String xs2PassPhrase;
        public String xs2Login;
        public String url;
        public String xs2Point;
    }

    public class ProviderBonus{
        public String type;
        public double value;
        public double fixValue;
    }

    public class RangeAmount{
        public int min;
        public int max;
    }

    public class Root{
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
        public Object commissionFromPartner;
        public Object commissionFromPayer;
        public CommissionFromRecipient commissionFromRecipient;
        public Object commissionFromProvider;
        public PartnerBonus partnerBonus;
        public Object createdAt;
        public Object updatedAt;
        public RangeAmount rangeAmount;
        public String logo;
        public String url;
        public boolean needIdentification;
        public ProcessingParams processingParams;
        public int paymentParametersGroupId;
        public boolean active;
        public boolean commissionsValid;
    }
}
