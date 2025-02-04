import './App.css';
import Login from './pages/Login'
import Home from './pages/Home';
import {Routes,Route, BrowserRouter} from 'react-router';
import SignUp from './pages/SignUp';

function App() {
  return (
    <BrowserRouter>
      <Routes>
      <Route path="/" element={<Home />} />
      <Route path='/login' element={<Login />} />
      <Route path='/register' element={<SignUp />} />
      </Routes>    
    </BrowserRouter>
  );
}

export default App;
