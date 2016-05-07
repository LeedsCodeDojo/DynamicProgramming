package dynamicprogramming;

class Result {
        final int value;
        final int noOfCoins;
        final int lastCoin;

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