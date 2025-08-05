package br.com.fiap.TechFood.application.domain.order;

public enum OrderStatus {
    DRAFT {
        @Override
        public OrderStatus next() {
            return PENDING_PAYMENT;
        }
    }, PENDING_PAYMENT {
        @Override
        public OrderStatus next() {
            return RECEIVED;
        }
    }, RECEIVED {
        @Override
        public OrderStatus next() {
            return IN_PROGRESS;
        }
    }, IN_PROGRESS {
        @Override
        public OrderStatus next() {
            return COMPLETED;
        }
    }, COMPLETED {
        @Override
        public OrderStatus next() {
            return FINISHED;
        }
    }, FINISHED {
        @Override
        public OrderStatus next() {
            throw new IllegalStateException("Request already completed. Cannot proceed further.");
        }
    };

    public abstract OrderStatus next();

    public boolean is(OrderStatus status) {
        return status == this;
    }

}
