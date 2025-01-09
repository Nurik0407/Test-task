INSERT INTO requisite (id, name, active, max_sum, created_at, updated_at)
VALUES (gen_random_uuid(), 'Requisite 1', true, 1000.00, NOW(), NOW()),
       (gen_random_uuid(), 'Requisite 2', false, 2000.00, NOW(), NOW()),
       (gen_random_uuid(), 'Requisite 3', true, 1500.00, NOW(), NOW());
