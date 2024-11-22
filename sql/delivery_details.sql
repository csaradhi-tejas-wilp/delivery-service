CREATE DATABASE deliverydb;

CREATE TABLE public.delivery_personnel (
    delivery_person_id SERIAL PRIMARY KEY,
    taking_deliveries BOOLEAN DEFAULT TRUE,
    overall_rating FLOAT DEFAULT 0.00,
    total_deliveries INTEGER DEFAULT 0
);

CREATE TABLE public.delivery_history (
    delivery_id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL,
    delivery_person_id INTEGER REFERENCES delivery_personnel(delivery_person_id),
    delivered BOOLEAN DEFAULT FALSE,
    message VARCHAR(255) DEFAULT 'NOT_PICKED_UP',
    accepted BOOLEAN DEFAULT FALSE, --to show explicitly that the user accepted the delivery
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    expected_time TIMESTAMP,
    rating FLOAT,
    rejected BOOLEAN DEFAULT FALSE ----to show explicitly that the user rejected the delivery
);
--if both accepted and rejected are false, then the order is assigned, but pending acceptance or rejection.

CREATE TABLE public.rejected_delivery (
    reject_id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL,
    delivery_person_id INTEGER REFERENCES delivery_personnel(delivery_person_id),
    rejection_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);