package dojodynamicprogramming;

class Result {
        int value;
        int noOfCoins;
        int lastCoin;

        public Result(int noOfCoins, int value, int lastCoin) {
            this.noOfCoins = noOfCoins;
            this.value = value;
            this.lastCoin = lastCoin;
        }

        @Override
        public String toString() {
            return "{" + "value=" + value + ", noOfCoins=" + noOfCoins + ", lastCoin=" + lastCoin + '}';
        }
    }