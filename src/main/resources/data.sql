-- Clean up existing data to avoid primary key conflicts
DELETE FROM product;

INSERT INTO product (id, name, price, quantity, description, image_url, category)
VALUES (1, 'Quantum-X Wireless Mouse', 4500.0, 100, 'Ergonomic 25k DPI gaming mouse with RGB.', 'https://images.unsplash.com/photo-1527676184771-45744fca3713?w=500', 'Peripherals');

INSERT INTO product (id, name, price, quantity, description, image_url, category)
VALUES (2, 'Neural-Link VR Headset', 89000.0, 50, 'Immersive 4K standalone VR experience.', 'https://images.unsplash.com/photo-1622979135225-d2ba269cf1ac?w=500', 'Wearables');

INSERT INTO product (id, name, price, quantity, description, image_url, category)
VALUES (3, 'ScaleStore Pro Keyboard', 12500.0, 75, 'Mechanical tactile switches with aluminum frame.', 'https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?w=500', 'Peripherals');

INSERT INTO product (id, name, price, quantity, description, image_url, category)
VALUES (4, 'Ultra-Wide Curved Monitor', 45000.0, 30, '34-inch 144Hz curved display for multitasking.', 'https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?w=500', 'Displays');

INSERT INTO product (id, name, price, quantity, description, image_url, category)
VALUES (5, 'Titan Desktop PC', 155000.0, 10, 'RTX 4090 powered beast for high-end gaming.', 'https://images.unsplash.com/photo-1587831990711-23ca6441447b?w=500', 'Systems');