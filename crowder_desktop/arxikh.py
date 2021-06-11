import tkinter as tk
from tkinter import ttk
from tkinter import *
from PIL import ImageTk, Image
from tkinter import messagebox
import pyrebase
import mysql.connector

root = tk.Tk()

# Δημιουργία style
style = ttk.Style(root)
root.tk.call('source', 'azure.tcl')
style.theme_use('azure')
root.option_add('*Font', "Arial")
bg = PhotoImage(file="crowder(1).png")

#icon-ονομασία
root.title("Crowder")
root.iconbitmap("favicon.ico")

#διαστάσεις παραθύρου
window_width = 900
window_height = 700
screen_width = root.winfo_screenwidth()
screen_height = root.winfo_screenheight()
center_x = int(screen_width/2 - window_width / 2)
center_y = int(screen_height/2 - window_height / 2)
root.geometry(f'{window_width}x{window_height}+{center_x}+{center_y}')
root.resizable(False, False)

my_label = Label(root, image=bg)
my_label.place(x=0, y=0, relwidth=1, relheight=1)

#firebase
firebaseConfig = {
    "apiKey": "AIzaSyDgH0lE99lAwzlwDra4kJ2mYj1Llsbsbi8",
    "authDomain": "crowder-54fe7.firebaseapp.com",
    "databaseURL":"https://console.firebase.google.com/u/0/project/crowder-54fe7/authentication/users/",
    "projectId": "crowder-54fe7",
    "storageBucket": "crowder-54fe7.appspot.com",
    "messagingSenderId": "49190602703",
    "appId": "1:49190602703:web:73eada9ca7a6a9294f34ba"
  }
firebase = pyrebase.initialize_app(firebaseConfig)
auth = firebase.auth()
storage = firebase.storage()
db = firebase.database()

#Σύνδεση με την sql
mydb = mysql.connector.connect(
  host="35.234.101.122",
  user="root",
  password="kwdikos123",
  database="crowder"
)

mycursor = mydb.cursor()


def exitt():
    root.destroy()
    import main


def minus():
    new = int(counter.get()) - 1
    counter.delete(0,END)
    counter.insert(0, new)

def add():
    new = int(counter.get()) + 1
    counter.delete(0, END)
    counter.insert(0, new)

def database():
    sql = "INSERT INTO store ( VALUES( %s,%s,%s,%s,%s,%s)"
    val = (email.get(), 0, username.get(), city.get(), address.get(), postcode.get())
    mycursor.execute(sql,val)

#buttons
button = ttk.Button(text="Έξοδος", command=exitt)
button.place(x=5, y=660)

def window():
    global counter
    label = Label(root,text="Αριθμός πελατών τώρα:",font=("Arial", 15))
    label.place(x=180, y=50)
    button = ttk.Button(text="-", command=minus)
    button.place(x=80, y=90)
    button = ttk.Button(text="+", command=add)
    button.place(x=390, y=90)
    counter = ttk.Entry(root, textvariable="counter")
    counter.place(x=180, y=90)
    counter.insert(0,'0')
    label = Label(root,text="Τα ραντεβού μου:",font=("Arial", 20))
    label.place(x=80, y=300)

def notebook():
    notebook = ttk.Notebook(root)
    notebookTab1 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab1, text='9:00-10:00')
    notebookTab2 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab2, text='10:00-11:00')
    notebookTab3 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab3, text='11:00-12:00')
    notebookTab4 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab4, text='12:00-13:00')
    notebookTab5 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab5, text='13:00-14:00')
    notebookTab6 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab6, text='14:00-15:00')
    notebookTab7 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab7, text='17:00-18:00')
    notebookTab8 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab8, text='18:00-19:00')
    notebookTab9 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab9, text='19:00-20:00')
    notebookTab10 = ttk.Frame(notebook, width=335, height=150)
    notebook.add(notebookTab10, text='20:00-21:00')
    notebook.place(x=50, y=430)
    ttk.Label(notebookTab1, text="9:49 - silia").pack()
window()
notebook()

root.mainloop()
