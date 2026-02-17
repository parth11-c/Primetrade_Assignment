import React, { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import api from '../api';
import { LogOut, Plus, Trash2, CheckCircle, Circle, LayoutDashboard } from 'lucide-react';

const Dashboard = () => {
    const { logout } = useAuth();
    const [tasks, setTasks] = useState([]);
    const [newTask, setNewTask] = useState({ title: '', description: '', status: 'PENDING' });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = async () => {
        try {
            const res = await api.get('/tasks');
            setTasks(res.data);
        } catch (err) {
            console.error('Failed to fetch tasks');
        }
    };

    const handleCreateTask = async (e) => {
        e.preventDefault();
        if (!newTask.title) return;
        setLoading(true);
        try {
            await api.post('/tasks', newTask);
            setNewTask({ title: '', description: '', status: 'PENDING' });
            fetchTasks();
        } catch (err) {
            console.error('Failed to create task');
        } finally {
            setLoading(false);
        }
    };

    const handleToggleStatus = async (task) => {
        const updatedStatus = task.status === 'PENDING' ? 'COMPLETED' : 'PENDING';
        try {
            await api.put(`/tasks/${task.id}`, { ...task, status: updatedStatus });
            fetchTasks();
        } catch (err) {
            console.error('Failed to update task');
        }
    };

    const handleDeleteTask = async (id) => {
        try {
            await api.delete(`/tasks/${id}`);
            fetchTasks();
        } catch (err) {
            console.error('Failed to delete task');
        }
    };

    return (
        <div className="container">
            <header style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '3rem' }}>
                <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
                    <LayoutDashboard size={32} color="#6366f1" />
                    <h1 style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>Dashboard</h1>
                </div>
                <button onClick={logout} className="btn" style={{ background: '#334155', color: 'white' }}>
                    <LogOut size={18} /> Logout
                </button>
            </header>

            <div style={{ display: 'grid', gridTemplateColumns: 'minmax(0, 1fr) 2fr', gap: '2rem' }}>
                {/* Create Task Form */}
                <div className="glass" style={{ padding: '1.5rem', height: 'fit-content' }}>
                    <h2 style={{ fontSize: '1.25rem', marginBottom: '1.5rem', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <Plus size={20} /> New Task
                    </h2>
                    <form onSubmit={handleCreateTask} style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                        <input
                            className="input"
                            placeholder="Task Title"
                            value={newTask.title}
                            onChange={(e) => setNewTask({ ...newTask, title: e.target.value })}
                            required
                        />
                        <textarea
                            className="input"
                            placeholder="Description (optional)"
                            rows="3"
                            style={{ resize: 'none' }}
                            value={newTask.description}
                            onChange={(e) => setNewTask({ ...newTask, description: e.target.value })}
                        />
                        <button className="btn btn-primary" style={{ justifyContent: 'center' }} disabled={loading}>
                            {loading ? 'Adding...' : 'Add Task'}
                        </button>
                    </form>
                </div>

                {/* Task List */}
                <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                    {tasks.length === 0 ? (
                        <div className="glass" style={{ padding: '3rem', textAlign: 'center', color: '#94a3b8' }}>
                            No tasks found. Create one to get started!
                        </div>
                    ) : (
                        tasks.map((task) => (
                            <div key={task.id} className="glass" style={{ padding: '1.25rem', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
                                    <button onClick={() => handleToggleStatus(task)} style={{ background: 'none', border: 'none', cursor: 'pointer', color: task.status === 'COMPLETED' ? '#10b981' : '#94a3b8' }}>
                                        {task.status === 'COMPLETED' ? <CheckCircle size={24} /> : <Circle size={24} />}
                                    </button>
                                    <div>
                                        <h3 style={{ fontSize: '1.1rem', textDecoration: task.status === 'COMPLETED' ? 'line-through' : 'none', color: task.status === 'COMPLETED' ? '#94a3b8' : 'white' }}>
                                            {task.title}
                                        </h3>
                                        <p style={{ fontSize: '0.875rem', color: '#94a3b8' }}>{task.description}</p>
                                    </div>
                                </div>
                                <button onClick={() => handleDeleteTask(task.id)} style={{ background: 'none', border: 'none', cursor: 'pointer', color: '#f43f5e' }}>
                                    <Trash2 size={20} />
                                </button>
                            </div>
                        ))
                    )}
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
