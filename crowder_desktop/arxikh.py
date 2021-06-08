from tkinter import ttk
from tkinter import *
from PIL import ImageTk, Image
import sqlite3

root = Tk()

# Δημιουργία αρχικής οθόνης
style = ttk.Style(root)
root.tk.call('source', 'azure.tcl')
style.theme_use('azure')
root.title("Crowder")
root.iconbitmap("favicon.ico")
root.geometry("900x700")
root.option_add('*Font', "Arial")
bg = PhotoImage(file="crowder(1).png")
my_label = Label(root, image=bg)
my_label.place(x=0, y=0, relwidth=1, relheight=1)

button = Button(text="Εγγράψου!", height="2", width="30", bg='#FFA6F9')
button.pack()


root.mainloop()
