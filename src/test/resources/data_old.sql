-- TABLA MEDICO
INSERT INTO medico (id, nombre, especialidad, correo, telefono) VALUES
(1, 'Dr. Juan Pérez', 'Cardiología', 'juan.perez@hospital.com', '3100000001'),
(2, 'Dra. María López', 'Pediatría', 'maria.lopez@hospital.com', '3100000002'),
(3, 'Dr. Carlos Gómez', 'Neurología', 'carlos.gomez@hospital.com', '3100000003'),
(4, 'Dra. Ana Torres', 'Dermatología', 'ana.torres@hospital.com', '3100000004'),
(5, 'Dr. Luis Herrera', 'Oftalmología', 'luis.herrera@hospital.com', '3100000005');

 Reiniciar contador del ID autoincremental
ALTER TABLE medico ALTER COLUMN id RESTART WITH 6;

-- TABLA PACIENTE

INSERT INTO paciente (id, nombre, documento, telefono, correo, direccion) VALUES
(1, 'Carlos Pérez', '123456789', '3101111111', 'carlos.perez@gmail.com', 'Cra 10 # 20-30'),
(2, 'Laura Gómez', '987654321', '3102222222', 'laura.gomez@gmail.com', 'Cl 50 # 40-20'),
(3, 'Andrés Torres', '135792468', '3103333333', 'andres.torres@gmail.com', 'Cra 15 # 30-25'),
(4, 'María Rodríguez', '246813579', '3104444444', 'maria.rodriguez@gmail.com', 'Cl 80 # 55-60'),
(5, 'Juliana Ramírez', '112233445', '3105555555', 'juliana.ramirez@gmail.com', 'Cra 25 # 10-15');

-- Reiniciar contador del ID autoincremental
ALTER TABLE paciente ALTER COLUMN id RESTART WITH 6;

-- TABLA CITA

INSERT INTO cita (id, fecha, hora, motivo, id_medico, id_paciente) VALUES
(1, '2025-10-10', '08:00:00', 'Chequeo general', 1, 1),
(2, '2025-10-11', '09:30:00', 'Control mensual', 2, 1),
(3, '2025-10-12', '10:00:00', 'Dolor de cabeza', 3, 2),
(4, '2025-10-13', '15:00:00', 'Revisión', 4, 2),
(5, '2025-10-14', '16:30:00', 'Seguimiento', 5, 3);


