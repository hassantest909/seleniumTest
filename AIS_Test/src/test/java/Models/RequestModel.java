package Models;

import java.util.ArrayList;
import java.util.List;


public class RequestModel {
   
    public static class Data {
        private Security security;
		private Account account;
        private String channel ="";
        private String terminal="";
        private String reterivalReferenceNumber="";
        private Payload payLoad;
        private List<Object> additionalInformation;
        private String checkSum="";
        
        public Security getSecurity() {
			return security;
		}
		public void setSecurity(Security security) {
			this.security = security;
		}
		public Account getAccount() {
			return account;
		}
		public void setAccount(Account account) {
			this.account = account;
		}
		public String getChannel() {
			return channel;
		}
		public void setChannel(String channel) {
			this.channel = channel;
		}
		public String getTerminal() {
			return terminal;
		}
		public void setTerminal(String terminal) {
			this.terminal = terminal;
		}
		public String getReterivalReferenceNumber() {
			return reterivalReferenceNumber;
		}
		public void setReterivalReferenceNumber(String reterivalReferenceNumber) {
			this.reterivalReferenceNumber = reterivalReferenceNumber;
		}
		public Payload getPayLoad() {
			return payLoad;
		}
		public void setPayLoad(Payload payLoad) {
			this.payLoad = payLoad;
		}
		public List<Object> getAdditionalInformation() {
			return additionalInformation;
		}
		public void setAdditionalInformation(ArrayList<Object> arrayList) {
			this.additionalInformation = arrayList;
		}
		public String getCheckSum() {
			return checkSum;
		}
		public void setCheckSum(String checkSum) {
			this.checkSum = checkSum;
		}

        // Getters and setters for other properties
        
		/*
		 * public Security getSecurity() { return security; }
		 * 
		 * public void setSecurity(Security security) { this.security = security; }
		 * 
		 * public Payload getPayLoad() { return payLoad; }
		 * 
		 * public void setPayLoad(Payload payLoad) { this.payLoad = payLoad; }
		 * 
		 * public List<AdditionalInformation> getAdditionalInformation() { return
		 * additionalInformation; }
		 * 
		 * public void setAdditionalInformation(List<AdditionalInformation>
		 * additionalInformation) { this.additionalInformation = additionalInformation;
		 * }
		 */
    }

    public static class Security {
        private String userName="";
        private String password="";
        private String securityToken="";

        // Getters and setters

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSecurityToken() {
            return securityToken;
        }

        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }
    }

    public static class Account {
        private String msidn="";
        private String iban="";
        private String bban="";
        private String pan="";
        private String currency="";

        // Getters and setters

        public String getMsidn() {
            return msidn;
        }

        public void setMsidn(String msidn) {
            this.msidn = msidn;
        }

        public String getIban() {
            return iban;
        }

        public void setIban(String iban) {
            this.iban = iban;
        }

        public String getBban() {
            return bban;
        }

        public void setBban(String bban) {
            this.bban = bban;
        }

        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }

    public static class Payload {
        private String userName="";
        private String userPassword="";

        // Getters and setters

        public String getUserName() {
            return userName="";
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }
    }

    public static class AdditionalInformation {
        private String infoKey="";
        private String infoValue="";

        // Getters and setters

        public String getInfoKey() {
            return infoKey;
        }

        public void setInfoKey(String infoKey) {
            this.infoKey = infoKey;
        }

        public String getInfoValue() {
            return infoValue;
        }

        public void setInfoValue(String infoValue) {
            this.infoValue = infoValue;
        }
    }
    
 private Data data;
    
    public RequestModel() {
        this.data = new Data(); // Initialize the data object
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
