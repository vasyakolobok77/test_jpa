create table if not exists public.test_items (
    id int4 primary key,
    ts timestamp,
    tstz timestamp with time zone
);
