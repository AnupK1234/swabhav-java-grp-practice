<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Apply for Personal Leave</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body, .card, .form-control, .btn, label, h3, .text-muted {
            font-family: 'Poppins', Arial, sans-serif !important;
        }
        h3 {
            font-weight: 700;
            font-size: 2rem;
            color: #232323;
            margin-bottom: 1.5rem;
        }
        .modern-card {
            background: #fff;
            border-radius: 1.5rem;
            box-shadow: 0 6px 28px rgba(44,62,80,0.18), 0 1.5px 7px rgba(44,62,80,0.09);
            padding: 2.2rem 2.3rem 2.2rem 2.3rem;
            margin-bottom: 2rem;
            border: 1px solid #f1f4f6;
            max-width: 520px;
        }
        .text-muted {
            color: #7b8994 !important;
            font-size: 1.08rem;
            margin-bottom: 2.2rem;
        }
        .modern-form {
            display: flex;
            flex-direction: column;
            gap: 1.3rem;
        }
        .form-label {
            font-weight: 600;
            font-size: 1rem;
            color: #232323;
            margin-bottom: 0.4rem;
        }
        .form-control {
            border-radius: 0.8rem;
            border: 1px solid #e3e6ea;
            background: #f7fbfc;
            font-size: 1.08rem;
            padding: 0.7rem 1.1rem;
            min-height: 44px;
            transition: border 0.2s;
            box-shadow: none;
        }
        .form-control:focus {
            border-color: #1c62f6;
            background: #fff;
            outline: none;
        }
        textarea.form-control {
            min-height: 80px;
            resize: vertical;
        }
        .btn-primary {
            background: linear-gradient(90deg, #1c62f6 0%, #40e0d0 100%);
            border: none;
            border-radius: 0.8rem;
            font-weight: 600;
            font-size: 1.1rem;
            padding: 0.7rem 1.9rem;
            transition: background 0.18s, box-shadow 0.15s;
            box-shadow: 0 3px 18px rgba(44,62,80,0.09);
        }
        .btn-primary:hover, .btn-primary:focus {
            background: linear-gradient(90deg, #40e0d0 0%, #1c62f6 100%);
            box-shadow: 0 8px 32px rgba(44,62,80,0.13);
        }
        @media (max-width: 600px) {
            .modern-card { padding: 1.1rem 0.7rem; }
            h3 { font-size: 1.2rem;}
        }
    </style>
</head>
<body>
    <h3>Apply for Personal Leave</h3>
    <div class="modern-card">
        <p class="text-muted">Your leave applications will be sent to the Admin for approval.</p>
        <form action="${pageContext.request.contextPath}/manager" method="post" class="modern-form">
            <input type="hidden" name="action" value="applyLeave">
            <div>
                <label for="startDate" class="form-label">Start Date</label>
                <input type="date" class="form-control" id="startDate" name="startDate" required>
            </div>
            <div>
                <label for="endDate" class="form-label">End Date</label>
                <input type="date" class="form-control" id="endDate" name="endDate" required>
            </div>
            <div>
                <label for="reason" class="form-label">Reason for Leave</label>
                <textarea class="form-control" id="reason" name="reason" rows="3" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit Application</button>
        </form>
    </div>
</body>
</html>