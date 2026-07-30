-- Otel 1, oda tipi 10 icin 30 gunluk envanter (her gun 5 oda)
INSERT INTO room_type_inventory (hotel_id, room_type_id, inventory_date, total_inventory, total_reserved, version)
SELECT 1, 10, DATEADD('DAY', X, CURRENT_DATE), 5, 0, 0
FROM SYSTEM_RANGE(0, 29);
