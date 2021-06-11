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

#icon-ονομασία
root.title("Crowder")
root.iconbitmap("favicon.ico")

logo = ImageTk.PhotoImage(Image.open("crowder.png"))
label = Label(image=logo)
label.pack()

#διαστάσεις παραθύρου
window_width = 900
window_height = 700
screen_width = root.winfo_screenwidth()
screen_height = root.winfo_screenheight()
center_x = int(screen_width/2 - window_width / 2)
center_y = int(screen_height/2 - window_height / 2)
root.geometry(f'{window_width}x{window_height}+{center_x}+{center_y}')

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



def register():
    global email, password, checkpassword, username,city,address,postcode
    #πρώτη στήλη
    Label(root, text="Εισάγετε Username:").place(x=200, y=310)
    username = ttk.Entry(root, textvariable="username")
    username.place(x=200, y=340)
    Label(root, text="Εισάγετε Email:").place(x=200, y=310)(x=200, y=390)
    email = ttk.Entry(root, textvariable="email")
    email.place(x=200, y=420)
    Label(root, text="Εισάγετε κωδικό σύνδεσης:").place(x=200, y=470)
    password = ttk.Entry(root, textvariable="password", show='●')
    password.place(x=200, y=500)
    Label(root, text="Επαλήθευση κωδικού σύνδεσης:").place(x=200, y=550)
    checkpassword = ttk.Entry(root, textvariable="checkpassword", show='●')
    checkpassword.place(x=200, y=580)

    #δεύτερη στήλη
    Label(root, text="Πόλη:").place(x=450, y=310)
    city = ttk.Entry(root, textvariable="city")
    city.place(x=450, y=340)
    Label(root, text="Διεύθυνση:").place(x=450, y=390)
    address = ttk.Entry(root, textvariable="address")
    address.place(x=450, y=420)
    Label(root, text="Ταχυδρομικός κώδικας:").place(x=450, y=470)
    postcode = ttk.Entry(root, textvariable="postcode")
    postcode.place(x=450, y=500)


    button = Button(text="Εγγράψου!", height="2", width="20", bg='#49A2F8', command=insert)
    button.place(x=450, y=560)
    root.bind('<Return>', inserte)


def insert():
    if password.get() == checkpassword.get():
        try:
            user = auth.create_user_with_email_and_password(email=email.get(), password=password.get())
            messagebox.showinfo("Crowder", "Συγχαρητήρια εγγραφήκατε!")
            database()
        except:
            messagebox.showwarning("Crowder", "To email χρησιμοποιείται ήδη")
    elif password.get() != checkpassword.get():
        messagebox.showwarning("Crowder", "Οι κωδικοί που εισάγατε διαφέρoυν μεταξύ τους")


def inserte(e):
    if ((email.get() != '') and (password.get() != '') and (checkpassword.get()!= '')):
        if password.get() == checkpassword.get():
            try:
                user = auth.create_user_with_email_and_password(email=email.get(), password=password.get())
                messagebox.showinfo("Crowder", "Συγχαρητήρια εγγραφήκατε!")
                database()
            except:
                messagebox.showwarning("Crowder", "To email χρησιμοποιείται ήδη")
        elif password.get() != checkpassword.get():
             messagebox.showwarning("Crowder", "Οι κωδικοί που εισάγατε διαφέρoυν μεταξύ τους")


def database():
    try:
        e = str(email.get())
        u = str(username.get())
        c = str(city.get())
        a = str(address.get())
        p = str(postcode.get())
        sql = "INSERT INTO store(storeID, people, name, city, address, postcode) VALUES( %s,%s,%s,%s,%s,%s)"
        val = (e, 0, u, c, a, p)
        mycursor.execute(sql, val)
        mydb.commit()
    except:
        print("error")

def exitt():
    root.destroy()
    import main

button = ttk.Button(text="Επιστροφή στην σύνδεση", command=exitt)
button.place(x=5, y=650)

register()


root.mainloop()
