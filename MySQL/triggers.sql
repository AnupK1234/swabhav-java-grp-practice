DELIMITER $$

CREATE TRIGGER before_product_insert
BEFORE INSERT ON products
FOR EACH ROW
BEGIN
    IF NEW.price < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Product price cannot be negative!';
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER after_employee_insert
AFTER INSERT ON employees
FOR EACH ROW
BEGIN
    INSERT INTO employee_audits (employee_id, change_type, new_salary, changed_by)
    VALUES (NEW.employee_id, 'INSERT', NEW.salary, USER());
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER after_employee_update
AFTER UPDATE ON employees
FOR EACH ROW
BEGIN
    IF OLD.salary <> NEW.salary THEN
        INSERT INTO employee_audits (employee_id, change_type, old_salary, new_salary, changed_by)
        VALUES (NEW.employee_id, 'UPDATE', OLD.salary, NEW.salary, USER());
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER after_employee_delete
AFTER DELETE ON employees
FOR EACH ROW
BEGIN
    INSERT INTO employee_audits (employee_id, change_type, old_salary, changed_by)
    VALUES (OLD.employee_id, 'DELETE', OLD.salary, USER());
END$$

DELIMITER ;
