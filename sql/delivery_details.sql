CREATE DATABASE deliverydb;

-- create delivery details table
CREATE TABLE public.delivery_details (
    order_id integer,
    delivery_person_id integer,
    delivered BOOLEAN DEFAULT FALSE,
    delivery_message VARCHAR(255) DEFAULT 'Not picked up',
    delivery_accepted BOOLEAN DEFAULT FALSE
);

-- insert sample details into delivery details table
INSERT INTO public.delivery_details (order_id,delivery_person_id,delivered,delivery_accepted) VALUES 
    ('4','5',false,'true');
INSERT INTO public.delivery_details (order_id,delivery_person_id,delivered,delivery_accepted) VALUES 
    ('7','12',true,'true');