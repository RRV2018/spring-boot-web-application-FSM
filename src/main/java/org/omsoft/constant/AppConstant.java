package org.omsoft.constant;

public enum AppConstant {
    ACTIVE {
        @Override
        public String toString(){
            return "ACTIVE";
        }
    },
    DEACTIVE {
        @Override
        public String toString(){
            return "DEACTIVE";
        }
    }
}
