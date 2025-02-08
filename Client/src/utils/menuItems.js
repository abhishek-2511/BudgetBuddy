import { dashboard, incomes, Transactions, expense } from "./icons";

export const menuItems = [
  {
    id: 1,
    title: "Dashboard",
    icon: dashboard,
    link: "/dashboard",
  },
  {
    id: 2,
    title: "View Transactions",
    icon: Transactions,
    link: "/transaction",
  },
  {
    id: 3,
    title: "Income",
    icon: incomes,
    link: "/income",
  },
  {
    id: 4,
    title: "Expenses",
    icon: expense,
    link: "/expense",
  },
];
