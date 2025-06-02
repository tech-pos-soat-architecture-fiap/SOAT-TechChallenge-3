package br.com.fiap.TechFood.application.core.domain.order;

public enum OrderStatus {
    DRAFT {
        @Override
        public OrderStatus next() {
            return RECEIVED;
        }
    },
    RECEIVED {
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

    public boolean isNotFinished() {
        return !FINISHED.equals(this);
    }
}
